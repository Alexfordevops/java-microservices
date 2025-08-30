package boxsystem.product_service.service;

import boxsystem.product_service.ExceptionHandler.productsExceptions.UserNotFoundException;
import boxsystem.product_service.dto.request.ProductRequestDTO;
import boxsystem.product_service.dto.response.ProductCreateResponseDTO;
import boxsystem.product_service.model.ProductModel;
import boxsystem.product_service.model.UserReadModel;
import boxsystem.product_service.repository.ProductRepository;
import boxsystem.product_service.repository.UserReadModelRepository;
import boxsystem.product_service.specification.ProductSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserReadModelRepository userRepo;

    @Transactional
    public ProductCreateResponseDTO createProductHeader(ProductRequestDTO data, String userName){

        //Econtra o usuario com este username
        UserReadModel user = userRepo.findByUsername(userName).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        //Verifica se o produto já existe para determinado usuario
        Optional<ProductModel> existingProductOptional = productRepo.findByNameAndUser(data.name, user);

        // Produto já existe, então atualiza a quantidade
        if (existingProductOptional.isPresent()) {

            //Coleto o produto do optional
            ProductModel existingProduct = existingProductOptional.get();

            //Soma a quantitdade existente com a nova quantidade inserida
            Double newQuantity = existingProduct.getQuantity() + data.quantity;

            //Salva o produto com as modificações
            existingProduct.setQuantity(newQuantity);
            productRepo.save(existingProduct);

            //Constroi o corpo de resposta
            ProductCreateResponseDTO response = new ProductCreateResponseDTO(
                    existingProduct.getId(),
                    existingProduct.getCreationDate(),
                    existingProduct.getUser(),
                    existingProduct.getName(),
                    existingProduct.getPrice(),
                    existingProduct.getCategory(),
                    existingProduct.getQuantity()
            );

            return response;

        }

        //Cria um produto com os dados de ProductRequestDTO
        ProductModel product = new ProductModel();
        product.setName(data.name);
        product.setCategory(data.category);
        product.setPrice(data.price);
        product.setQuantity(data.quantity);
        product.setUser(user);

        //Salva o produto
        ProductModel savedProduct = productRepo.save(product);

        //Mensagem de status
        String statusMessage = "Produto existente, quantidade incrementada";

        //Constroi o corpo de resposta
        ProductCreateResponseDTO response = new ProductCreateResponseDTO(
                savedProduct.getId(),
                savedProduct.getCreationDate(),
                savedProduct.getUser(),
                savedProduct.getName(),
                savedProduct.getPrice(),
                savedProduct.getCategory(),
                savedProduct.getQuantity()
        );

        return response;
    }

    //Lista todos os produtos
    public List<ProductCreateResponseDTO> listAllProducts(){

        List<ProductModel> products = productRepo.findAll();

        List<ProductCreateResponseDTO> productDTOs = products.stream()
                .map(savedProduct -> new ProductCreateResponseDTO(
                        savedProduct.getId(),
                        savedProduct.getCreationDate(),
                        savedProduct.getUser(),
                        savedProduct.getName(),
                        savedProduct.getPrice(),
                        savedProduct.getCategory(),
                        savedProduct.getQuantity()
                ))
                .collect(Collectors.toList());

        return productDTOs;

    }

    //Lista produtos por usuario
    public List<ProductCreateResponseDTO> listProductByUser(String username){

        //Busca o usuario no repositorio pelo username
        UserReadModel user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        //Com o id do usuario encontrado lista todos os produtos deste usuario
        List<ProductModel> products = productRepo.findByUser_Id(user.getId());

        //Converte a lista para DTO
        List<ProductCreateResponseDTO> productDTOs = products.stream()
                .map(savedProduct -> new ProductCreateResponseDTO(
                        savedProduct.getId(),
                        savedProduct.getCreationDate(),
                        savedProduct.getUser(),
                        savedProduct.getName(),
                        savedProduct.getPrice(),
                        savedProduct.getCategory(),
                        savedProduct.getQuantity()
                ))
                .collect(Collectors.toList());

        return productDTOs;

    }

    public List<ProductCreateResponseDTO> listProductByUserFilter(
            String username,
            String name,
            String category,
            Double minPrice,
            Double maxPrice,
            Double minQuantity,
            Double maxQuantity
    ){
        //Busca o usuario no repositorio pelo username
        UserReadModel user = userRepo.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        //Pega o id do usuario encontrado com o username
        Long userId = user.getId();

        //Com o id do usuario encontrado lista todos os produtos deste usuario
        //List<ProductModel> products = productRepo.findByUser_Id(user.getId());

        //Constroi a lista de especificacoes
        Specification<ProductModel> spec = Specification.where(ProductSpecification.belongsToUser(userId))
                .and(ProductSpecification.hasName(name))
                .and(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.priceGreaterThanOrEqual(minPrice))
                .and(ProductSpecification.priceLessThanOrEqual(maxPrice))
                .and(ProductSpecification.quantityGreaterThanOrEqual(minQuantity))
                .and(ProductSpecification.quantityLessThanOrEqual(maxQuantity));

        //Busca todos os produtos com as especificacoes criadas
        List<ProductModel> products = productRepo.findAll(spec);

        //Converte a lista para DTO
        List<ProductCreateResponseDTO> productDTOs = products.stream()
                .map(savedProduct -> new ProductCreateResponseDTO(
                        savedProduct.getId(),
                        savedProduct.getCreationDate(),
                        savedProduct.getUser(),
                        savedProduct.getName(),
                        savedProduct.getPrice(),
                        savedProduct.getCategory(),
                        savedProduct.getQuantity()
                ))
                .collect(Collectors.toList());

        return productDTOs;
    }

    @Transactional
    //Deleta todos os produtos
    public List<ProductCreateResponseDTO> deleteAllProducts(){

        //Lista todos os produtos
        List<ProductModel> deletedProducts = productRepo.findAll();

        //Deleta todos os produtos
        productRepo.deleteAll();

        //Converte a lista
        List<ProductCreateResponseDTO> productDTOs = deletedProducts.stream()
                .map(product -> new ProductCreateResponseDTO(
                        product.getId(),
                        product.getCreationDate(),
                        product.getUser(),
                        product.getName(),
                        product.getPrice(),
                        product.getCategory(),
                        product.getQuantity()
                ))
                .collect(Collectors.toList());

        return productDTOs;
    }
}
