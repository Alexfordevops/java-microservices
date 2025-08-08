package boxsystem.auth_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
public class UserModel {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //username
    @Column(unique = true)
    private String username;

    //password
    @Column
    private String password;

    //role
    @Column
    private String role = "user";

    //name
    @Column
    private String name;

    //Data de criação
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    //Callback para setar a data de criação automaticamente ===
    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    //Getters e Setters
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
