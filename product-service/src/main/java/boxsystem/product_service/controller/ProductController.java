package boxsystem.product_service.controller;

import boxsystem.product_service.dto.request.ProductRequestDTO;
import boxsystem.product_service.dto.response.ProductCreateResponseDTO;
import boxsystem.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/create/{username}")
    public ResponseEntity<ProductCreateResponseDTO> createProduct(
            @PathVariable String username,
            @Valid @RequestBody ProductRequestDTO data
            ){
        ProductCreateResponseDTO response = productService.createProductHeader(data, username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductCreateResponseDTO>> listAllProducts(){
        List<ProductCreateResponseDTO> response = productService.listAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
