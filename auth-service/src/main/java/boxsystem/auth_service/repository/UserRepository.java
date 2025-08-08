package boxsystem.auth_service.repository;

import boxsystem.auth_service.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Extende os metódos padão de consulta JPA
public interface UserRepository extends JpaRepository<UserModel, Long> {
    //Utiliza o tipo Opitinal<UserModel> pois o valor pode ou não estar presente
    //Pode retornar vazio
    Optional<UserModel> findByUsername(String username);
}
