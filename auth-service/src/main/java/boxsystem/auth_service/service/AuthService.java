package boxsystem.auth_service.service;

//import boxsystem.auth_service.dto.*;
import boxsystem.auth_service.ExceptionHandler.AuthExceptions.PasswordNotMatchException;
import boxsystem.auth_service.ExceptionHandler.AuthExceptions.UserNotFoundException;
import boxsystem.auth_service.ExceptionHandler.AuthExceptions.UsernameAlreadyExistsException;
import boxsystem.auth_service.dto.request.LoginDTO;
import boxsystem.auth_service.dto.request.RegisterDTO;
import boxsystem.auth_service.dto.response.AuthResponseDTO;
import boxsystem.auth_service.dto.response.RegisterResponseDTO;
import boxsystem.auth_service.model.UserModel;
import boxsystem.auth_service.repository.UserRepository;
import boxsystem.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    //Conecta com o repositório JPA
    @Autowired
    private UserRepository userRepository;

    //Conecta com o password encoder fornecido pelo spring security
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Conecta com o JWTUtil
    @Autowired
    private JwtUtil jwtUtil;

    //Registrar usuário
    public RegisterResponseDTO register(RegisterDTO data){

        //Verifica se o username já existe
        if(userRepository.findByUsername(data.username).isPresent()){
            throw new UsernameAlreadyExistsException("Username já existe");
        }

        //Cria novo usuário com a senha criptografada
        UserModel user = new UserModel();

        //Seta os dados do usuário vindos do DTO
        user.setUsername(data.username);
        user.setPassword(passwordEncoder.encode(data.password));
        user.setName(data.name);

        //Salva no banco de dados
        UserModel savedUser = userRepository.save(user);

        //Retorna DTO construido com os dados do usuario salvo
        return new RegisterResponseDTO(
                savedUser.getId(), //Gerado pelo sistema
                savedUser.getUsername(), //Gerado pelo usuário
                savedUser.getName(), //Gerado pelo usuário
                savedUser.getRole(), //Gerado pelo sistema
                savedUser.getCreationDate() //Gerado pelo sistema
        );
    }

    //Login do usuário
    public AuthResponseDTO login(LoginDTO data){

        //Busca pelo username, pode ou não existir por isso o optional
        Optional<UserModel> userOpt = userRepository.findByUsername(data.username);

        //Se retornar vazio, lança uma exceção
        if (userOpt.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        //Obtém os dados do objeto User
        UserModel user = userOpt.get();

        //Verifica se as senhas conferem
        //Se não conferir
        if (!passwordEncoder.matches(data.password, user.getPassword())){
            throw new PasswordNotMatchException("Senha não confere");
        }

        //Se conferir
        //Gera o token JWT
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDTO(token);
    }
}
