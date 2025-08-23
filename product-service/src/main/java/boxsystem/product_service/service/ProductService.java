package boxsystem.product_service.service;

import boxsystem.product_service.ExceptionHandler.productsExceptions.UserNotFoundException;
import boxsystem.product_service.dto.request.ProductRequestDTO;
import boxsystem.product_service.dto.response.ProductCreateResponseDTO;
import boxsystem.product_service.model.ProductModel;
import boxsystem.product_service.model.UserReadModel;
import boxsystem.product_service.repository.ProductRepository;
import boxsystem.product_service.repository.UserReadModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        //Cria um produto com os dados de ProductRequestDTO
        ProductModel product = new ProductModel();
        product.setName(data.name);
        product.setCategory(data.category);
        product.setPrice(data.price);
        product.setQuantity(data.quantity);
        product.setUser(user);

        //Salva o produto
        ProductModel savedProduct = productRepo.save(product);

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
}
