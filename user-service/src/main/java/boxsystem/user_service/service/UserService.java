package boxsystem.user_service.service;

import boxsystem.user_service.dto.UserResponseDTO;
import boxsystem.user_service.exceptionHandler.userExceptions.UserNotFoundException;
import boxsystem.user_service.models.UserDBReadModel;
import boxsystem.user_service.repository.UserDBReadModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDBReadModelRepository repo;

    //Busca todos os usuarios
    public List<UserResponseDTO> getAllUsers(){

        //Buscar todos os usuarios no repositorio
        List<UserDBReadModel> users = repo.findAll();

        //Converter Lista de usuarios para lista de DTO
        return users.stream()
                     .map(user -> new UserResponseDTO(
                             user.getId(),
                             user.getUsername(),
                             user.getName(),
                             user.getRole(),
                             user.getCreationDate()
                     ))
                     .collect(Collectors.toList());
    }

    //Busca usuario pelo id
    public UserResponseDTO getUserById(Long id){

        //Busca usuário pelo id
        UserDBReadModel user = repo.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        //Constroi o DTO de resposta
        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getCreationDate()
        );

        //Retorna o DTO construido
        return response;
    }

    //Busca usuario pelo name
    public List<UserResponseDTO> getUserByName(String name){

        //Busca usuário pelo name
        List<UserDBReadModel> users = repo.findByNameIgnoreCase(name);

        //Se a lista for vazia (nenhum usuário encontado pelo nome)
        if(users.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        //Converter Lista de usuarios para lista de DTO
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getRole(),
                        user.getCreationDate()
                ))
                .collect(Collectors.toList());
    }

    //Busca usuario pelo username
    public UserResponseDTO getUserByUsername(String username){

        //Busca usuário pelo id
        UserDBReadModel user = repo.findByUsernameIgnoreCase(username).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        //Constroi o DTO de resposta
        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getCreationDate()
        );

        //Retorna o DTO construido
        return response;
    }

    //Busca usuario pela data de criacao
    public List<UserResponseDTO> getUserByCreationDate(LocalDateTime creationDate){

        List<UserDBReadModel> users = repo.findByCreationDate(creationDate);

        if(users.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        //Converter Lista de usuarios para lista de DTO
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getRole(),
                        user.getCreationDate()
                ))
                .collect(Collectors.toList());
    }

    //Busca usuario pela role
    public List<UserResponseDTO> getUserByRole(String role){

        List<UserDBReadModel> users = repo.findByRoleIgnoreCase(role);

        if(users.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        //Converter Lista de usuarios para lista de DTO
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getRole(),
                        user.getCreationDate()
                ))
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getUserByNameAndRole(String name, String role){

        List<UserDBReadModel> users = repo.findByNameAndRoleIgnoreCase(name, role);

        if(users.isEmpty()){
            throw new UserNotFoundException("Usuário não encontrado");
        }

        //Converter Lista de usuarios para lista de DTO
        return users.stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getRole(),
                        user.getCreationDate()
                ))
                .collect(Collectors.toList());
    }
}
