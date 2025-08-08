package boxsystem.auth_service.controller;

//import boxsystem.auth_service.dto.*;
import boxsystem.auth_service.dto.request.LoginDTO;
import boxsystem.auth_service.dto.request.RegisterDTO;
import boxsystem.auth_service.dto.response.AuthResponseDTO;
import boxsystem.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth") //Todas as rotas aqui começaram com /auth
public class AuthController {

    @Autowired
    private AuthService authService;

    //Registro
    @PostMapping("register")
    public void register(@RequestBody RegisterDTO data){
        authService.register(data); //Registra novo usuário com o metodo da classe AuthService
    }

    //Login (retorna o AuthResponseDTO por meio do service)
    @PostMapping("login")
    public AuthResponseDTO login(@RequestBody LoginDTO data){
        return authService.login(data); //retorna JWT
    }
}
