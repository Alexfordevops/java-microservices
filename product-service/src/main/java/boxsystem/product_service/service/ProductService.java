package boxsystem.product_service.service;

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
        UserReadModel user = userRepo.findByUsername(userName).orElseThrow();

        //Cria um produto com os dados de ProductRequestDTO
        ProductModel product = new ProductModel();
        product.setName(data.name);
        product.setCategory(data.category);
        product.setPrice(data.price);
        product.setQuantity(data.quantity);
        product.setUser(user);

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
}
