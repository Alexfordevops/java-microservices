package boxsystem.auth_service.dto.request;
import jakarta.validation.constraints.NotBlank;

public class LoginDTO {

    @NotBlank(message = "O username é obrigatório")
    public String username;

    @NotBlank(message = "A senha é obrigatória")
    public String password;
}
