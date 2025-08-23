package boxsystem.product_service.repository;

import boxsystem.product_service.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    //Busca pela chave estrangeira com _Id
    List<ProductModel> findByUser_Id(Long id);
}
