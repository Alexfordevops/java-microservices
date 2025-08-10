package boxsystem.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

    @NotBlank(message = "O username é obrigatório")
    @Size(min = 4, max = 20, message = "O username deve ter entre 4 e 20 caracteres")
    public String username;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 100, message = "A senha deve ter pelo menos 6 caracteres")
    public String password;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    public String name;
}
