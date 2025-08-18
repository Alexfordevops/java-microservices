package boxsystem.user_service.controller;

import boxsystem.user_service.dto.UserResponseDTO;
import boxsystem.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}