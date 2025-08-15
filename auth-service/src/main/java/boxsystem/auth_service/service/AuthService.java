package boxsystem.auth_service.service;

import boxsystem.auth_service.ExceptionHandler.AuthExceptions.PasswordNotMatchException;
import boxsystem.auth_service.ExceptionHandler.AuthExceptions.UserNotFoundException;
import boxsystem.auth_service.ExceptionHandler.AuthExceptions.UsernameAlreadyExistsException;
import boxsystem.auth_service.dto.request.LoginDTO;
import boxsystem.auth_service.dto.request.RegisterDTO;
import boxsystem.auth_service.dto.request.UpdateUserDTO;
import boxsystem.auth_service.dto.response.AuthResponseDTO;
import boxsystem.auth_service.dto.response.RegisterResponseDTO;
import boxsystem.auth_service.model.UserModel;
import boxsystem.auth_service.repository.UserRepository;
import boxsystem.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt; // Biblioteca para criptografia de senha
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository; // Repositório JPA para usuários

    @Autowired
    private JwtUtil jwtUtil; // Classe utilitária para gerar token JWT

    @Autowired
    private UserService userService;

    // Registrar usuário
    public RegisterResponseDTO register(RegisterDTO data){

        // Verifica se o username já existe no banco
        if(userRepository.findByUsername(data.username).isPresent()){
            throw new UsernameAlreadyExistsException("Username já existe");
        }

        // Cria um novo usuário e criptografa a senha com BCrypt
        UserModel user = new UserModel();
        user.setUsername(data.username);
        user.setPassword(BCrypt.hashpw(data.password, BCrypt.gensalt())); // Criptografia da senha
        user.setName(data.name);

        // Chama o UserService
        return userService.register(user);

    }

    // Login do usuário
    public AuthResponseDTO login(LoginDTO data){

        // Busca usuário pelo username
        Optional<UserModel> userOpt = userRepository.findByUsername(data.username);

        // Se não encontrar, lança exceção
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        UserModel user = userOpt.get(); // Usuário encontrado

        // Valida a senha usando BCrypt
        if (!BCrypt.checkpw(data.password, user.getPassword())){
            throw new PasswordNotMatchException("Senha não confere");
        }

        // Gera token JWT contendo apenas o username
        String token = jwtUtil.generateToken(user.getUsername());

        // Retorna DTO com o token
        return new AuthResponseDTO(token);
    }

    //Update de usuario
    public RegisterResponseDTO update(Long id, UpdateUserDTO dto){

        // Busca usuário pelo username
        Optional<UserModel> userOpt = userRepository.findById(id);

        // Se não encontrar, lança exceção
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        //Chama o UserService
        return userService.update(id, dto);
    }

    //Deleta usuario
    public RegisterResponseDTO delete(Long id){

        // Busca usuário pelo username
        Optional<UserModel> userOpt = userRepository.findById(id);

        // Se não encontrar, lança exceção
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        //Chama o UserService
        return userService.delete(id);
    }
}
