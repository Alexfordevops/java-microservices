package boxsystem.product_service.repository;

import boxsystem.product_service.model.ProductModel;
import boxsystem.product_service.model.UserReadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    //Busca pela chave estrangeira com _Id
    List<ProductModel> findByUser_Id(Long id);

    //Busca produto pelo nome
    Optional<ProductModel> findByName(String name);

    //Busca produto pelo nome e usuario atribuido
    Optional<ProductModel> findByNameAndUser(String name, UserReadModel user);
}
