package boxsystem.user_service.rabbitmq.payload;

import java.time.LocalDateTime;

public class UserPayload {
    private Long id;
    private String username;
    private String name;
    private String role;
    private LocalDateTime creationDate;

    public UserPayload() {}

    public UserPayload(Long id, String username, String name, String role, LocalDateTime creationDate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.creationDate = creationDate;
    }

    // getters e setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
