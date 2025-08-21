package boxsystem.product_service.controller;

import boxsystem.product_service.model.ProductModel;
import boxsystem.product_service.model.UserReadModel;
import boxsystem.product_service.repository.ProductRepository;
import boxsystem.product_service.repository.UserReadModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class TableTest {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private UserReadModelRepository userRepo;

    @GetMapping("/create/{username}")
    public ProductModel createProduct(
            @PathVariable String username
    ){
        UserReadModel user = userRepo.findByUsername(username).orElseThrow();

        ProductModel product = new  ProductModel(
                "martelo",
                10.0,
                "ferramentas",
                user
        );
        //this.productRepo.save(product);
        return product;
    }

    @GetMapping("/list")
    public List<ProductModel> listProduct(){
        return productRepo.findAll();
    }

    @GetMapping("/users")
    public List<UserReadModel> listUsers(){
        return userRepo.findAll();
    }
}
