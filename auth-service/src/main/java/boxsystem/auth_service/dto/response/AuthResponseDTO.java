package boxsystem.auth_service.dto.response;

public class AuthResponseDTO {

    //Token para o usuário autenticar
    public String token;

    public AuthResponseDTO(String token){
        this.token = token;
    }
}
