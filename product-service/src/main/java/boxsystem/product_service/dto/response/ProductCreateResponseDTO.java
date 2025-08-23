package boxsystem.product_service.dto.response;

import boxsystem.product_service.model.UserReadModel;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class ProductCreateResponseDTO {

    private Long id;
    private String name;
    private String category;
    private Double price;
    private Double quantity;
    private LocalDateTime creationDate;
    private Long userId;


    public ProductCreateResponseDTO(){};

    public ProductCreateResponseDTO(
            Long id,
            LocalDateTime creationDate,
            UserReadModel user,
            String name,
            Double price,
            String category,
            Double quantity
    ){
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.userId = user.getId();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public Long getUser() {
        return userId;
    }
    public void setUser(Long user) {
        this.userId = user;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
