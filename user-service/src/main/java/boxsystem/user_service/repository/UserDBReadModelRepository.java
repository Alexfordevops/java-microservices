package boxsystem.user_service.repository;

import boxsystem.user_service.models.UserDBReadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserDBReadModelRepository extends JpaRepository<UserDBReadModel, Long> {

    //Busca usuario por nome
    List<UserDBReadModel> findByNameIgnoreCase(String name);

    //Busca usuario por username
    Optional<UserDBReadModel> findByUsernameIgnoreCase(String name);

    //Busca usuario por data de criaçao (adaptar formato na entrada)
    List<UserDBReadModel> findByCreationDate(LocalDateTime creationDate);

    //Busca usuario por role
    List<UserDBReadModel> findByRoleIgnoreCase(String role);

    //Busca usuario por name e role especifica
    List<UserDBReadModel> findByNameAndRoleIgnoreCase(String name, String role);
}
