package boxsystem.product_service.repository;

import boxsystem.product_service.model.UserReadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReadModelRepository extends JpaRepository<UserReadModel, Long> {

    Optional<UserReadModel> findByUsername(String name);
}
