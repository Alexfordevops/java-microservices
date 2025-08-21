package boxsystem.product_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "productTable")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;
    private LocalDateTime creationDate = LocalDateTime.now();

    // Chave estrangeira -> UserReadModel
    @ManyToOne
    @JoinColumn(name = "user_id") // nome da coluna que será a foreign key
    private UserReadModel user;

    public ProductModel() {}

    public ProductModel(String name, Double price, String category, UserReadModel user) {
        //this.id = id;
        this.name = name;
        //this.creationDate = creationDate;
        this.price = price;
        this.category = category;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public UserReadModel getUser() {
        return user;
    }
    public void setUser(UserReadModel user) {
        this.user = user;
    }
}
