package boxsystem.auth_service.controller;

import boxsystem.auth_service.dto.request.LoginDTO;
import boxsystem.auth_service.dto.request.RegisterDTO;
import boxsystem.auth_service.dto.response.AuthResponseDTO;
import boxsystem.auth_service.dto.response.RegisterResponseDTO;
import boxsystem.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth") //Todas as rotas aqui começaram com /auth
public class AuthController {

    @Autowired
    private AuthService authService;

    //Registro
    @PostMapping("register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterDTO data){
        RegisterResponseDTO createdUser = authService.register(data); //Registra novo usuário com o metodo da classe AuthService
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); //Resposta do servidor com httpstatus e corpo de resposta
    }

    //Login (retorna o AuthResponseDTO por meio do service)
    @PostMapping("login")
    public AuthResponseDTO login(@Valid @RequestBody LoginDTO data){
        return authService.login(data); //retorna JWT
    }
}
