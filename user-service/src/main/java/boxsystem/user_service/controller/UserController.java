package boxsystem.user_service.controller;

import boxsystem.user_service.dto.UserResponseDTO;
import boxsystem.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(@RequestHeader(value = "X-User-Id", required = false) String userId) {
        System.out.println("Requisição recebida para usuário: " + userId);
        List<UserResponseDTO> response = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Id", required = false) String ActualUserId
    ){
        System.out.println("Requisição recebida para usuário: " + ActualUserId);
        UserResponseDTO response = this.userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getUserByName/{name}")
    public ResponseEntity<List<UserResponseDTO>> getUserByName(
            @PathVariable String name,
            @RequestHeader(value = "X-User-Id", required = false) String ActualUserId
    ){
        System.out.println("Requisição recebida para usuário: " + ActualUserId);
        List<UserResponseDTO> response = this.userService.getUserByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(
            @PathVariable String username,
            @RequestHeader(value = "X-User-Id", required = false) String ActualUserId
    ){
        System.out.println("Requisição recebida para usuário: " + ActualUserId);
        UserResponseDTO response = this.userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getUserByCreationDate/{creationDate}")
    public ResponseEntity<List<UserResponseDTO>> getUserByCreationDate(
            @PathVariable LocalDateTime creationDate,
            @RequestHeader(value = "X-User-Id", required = false) String ActualUserId
    ){
        System.out.println("Requisição recebida para usuário: " + ActualUserId);
        List<UserResponseDTO> response = this.userService.getUserByCreationDate(creationDate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getUserByRole/{role}")
    public ResponseEntity<List<UserResponseDTO>> getUserByRole(
            @PathVariable String role,
            @RequestHeader(value = "X-User-Id", required = false) String ActualUserId
    ){
        System.out.println("Requisição recebida para usuário: " + ActualUserId);
        List<UserResponseDTO> response = this.userService.getUserByRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getUserByNameAndRole/{name}/{role}")
    public ResponseEntity<List<UserResponseDTO>> getUserByNameAndRole(
            @PathVariable String name,
            @PathVariable String role,
            @RequestHeader(value = "X-User-Id", required = false) String ActualUserId
    ){
        System.out.println("Requisição recebida para usuário: " + ActualUserId);
        List<UserResponseDTO> response = this.userService.getUserByNameAndRole(name, role);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}