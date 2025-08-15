package boxsystem.auth_service.service;

import boxsystem.auth_service.ExceptionHandler.AuthExceptions.UserNotFoundException;
import boxsystem.auth_service.dto.request.UpdateUserDTO;
import boxsystem.auth_service.dto.response.RegisterResponseDTO;
import boxsystem.auth_service.model.UserModel;
import boxsystem.auth_service.rabbitmq.UserEventPublisher;
import boxsystem.auth_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserEventPublisher publisher;

    //Registra novo usuario e dispara o evento
    @Transactional
    public RegisterResponseDTO register(UserModel user){

        //Salva o usuario no banco vindo de AuthService
        UserModel savedUser = repo.save(user);

        //Publica o envento user.created para o RabbitMQ
        publisher.publishUserCreated(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getRole()
        );

        //Retorna o DTO para o AuthService
        return new RegisterResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getRole(),
                savedUser.getCreationDate()
        );
    }

    //Atualiza usuario e dispara o evento
    @Transactional
    public RegisterResponseDTO update(Long id, UpdateUserDTO dto){

        //Busca o usuario pelo id
        UserModel user = repo.findById(id).get();

        //Atualiza os dados do usuario
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setName(dto.getName());

        UserModel savedUser = repo.save(user);

        //Public o evento
        publisher.publishUserUpdated(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getRole()
        );

        //Retorna o DTO
        return new RegisterResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getName(),
                savedUser.getRole(),
                savedUser.getCreationDate()
        );
    }

    //Deleta usuario e dispara o evento
    @Transactional
    public RegisterResponseDTO delete(Long id) {

        //Busca o usuario pelo id
        UserModel user = repo.findById(id).get();

        //Deleta o usuario
        repo.delete(user);

        //Publica o evento
        publisher.publishUserDeleted(user.getId());

        //Retorna o DTO
        return new RegisterResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getCreationDate()
        );
    }

}
