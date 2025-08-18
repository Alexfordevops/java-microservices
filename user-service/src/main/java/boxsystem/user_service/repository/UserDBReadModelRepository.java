package boxsystem.user_service.repository;

import boxsystem.user_service.models.UserDBReadModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDBReadModelRepository extends JpaRepository<UserDBReadModel, Long> {

}
