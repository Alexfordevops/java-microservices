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

    @PostMapping("/create")
    public ResponseEntity<ProductCreateResponseDTO> createProductXUserId(
            @Valid @RequestBody ProductRequestDTO data,
            @RequestHeader(value = "X-User-Id", required = false) String actualUser
    ){
        System.out.println("Requisição recebida para usuário: " + actualUser);
        ProductCreateResponseDTO response = productService.createProductHeader(data, actualUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<ProductCreateResponseDTO>> listAllProducts(){
        List<ProductCreateResponseDTO> response = productService.listAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/list/me")
    public ResponseEntity<List<ProductCreateResponseDTO>> listProductByUser(
            @RequestHeader(value = "X-User-Id", required = false) String actualUser
    ){
        System.out.println("Requisição recebida para usuário: " + actualUser);
        List<ProductCreateResponseDTO> response = productService.listProductByUser(actualUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<List<ProductCreateResponseDTO>> deleteAllProducts(){
        List<ProductCreateResponseDTO> response = productService.deleteAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
