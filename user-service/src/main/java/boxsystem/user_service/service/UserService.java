package boxsystem.user_service.service;

import boxsystem.user_service.dto.UserResponseDTO;
import boxsystem.user_service.models.UserDBReadModel;
import boxsystem.user_service.repository.UserDBReadModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDBReadModelRepository repo;

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
}
