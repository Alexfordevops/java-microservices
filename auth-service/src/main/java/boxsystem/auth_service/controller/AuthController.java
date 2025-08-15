package boxsystem.auth_service.controller;

import boxsystem.auth_service.dto.request.LoginDTO;
import boxsystem.auth_service.dto.request.RegisterDTO;
import boxsystem.auth_service.dto.request.UpdateUserDTO;
import boxsystem.auth_service.dto.response.AuthResponseDTO;
import boxsystem.auth_service.dto.response.RegisterResponseDTO;
import boxsystem.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth") // Todas as rotas aqui começam com /auth
public class AuthController {

    @Autowired
    private AuthService authService; // Injeção do serviço de autenticação

    // Registro de usuário
    @PostMapping("register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterDTO data){
        // Chama o metodo register do service, que salva o usuário no banco
        RegisterResponseDTO createdUser = authService.register(data);
        // Retorna status 201 CREATED com os dados do usuário criado
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Login do usuário (retorna JWT)
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO data){
        // Chama o metodo login do service, que valida usuário e gera token JWT
        AuthResponseDTO tokenJwt = authService.login(data);
        // Retorna status 200 OK com o token JWT no corpo
        return ResponseEntity.status(HttpStatus.OK).body(tokenJwt);
    }

    //Update do usuario
    @PutMapping("/update/{id}")
    public ResponseEntity<RegisterResponseDTO> update(
            @PathVariable Long id,
            @RequestBody UpdateUserDTO dto
            ){
        RegisterResponseDTO response = authService.update(id,dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Delete do usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RegisterResponseDTO> delete(@PathVariable Long id){
        RegisterResponseDTO response = authService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
