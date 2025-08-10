package boxsystem.auth_service.dto.response;

import java.time.LocalDateTime;

public class RegisterResponseDTO {

    //Atributos para construção do DTO
    private Long id;
    private String username;
    private String name;
    private String role;
    private LocalDateTime createdAt;

    //Instanciador do DTO
    public RegisterResponseDTO(
            Long id,
            String username,
            String name,
            String role,
            LocalDateTime createdAt
    ){
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
    }

    //Getters
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
