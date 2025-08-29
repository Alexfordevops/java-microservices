package boxsystem.product_service.specification;

import boxsystem.product_service.model.ProductModel;
import org.springframework.data.jpa.domain.Specification;
import java.math.BigDecimal;

public class ProductSpecification {

    //Nome do produto
    public static Specification<ProductModel> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    //Categoria do produto
    public static Specification<ProductModel> hasCategory(String category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(cb.lower(root.get("category")), category.toLowerCase());
    }

    //Preco maior ou igual
    public static Specification<ProductModel> priceGreaterThanOrEqual(Double minPrice) {
        return (root, query, cb) ->
                minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    //Preco menor ou igual
    public static Specification<ProductModel> priceLessThanOrEqual(Double maxPrice) {
        return (root, query, cb) ->
                maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    //Quantidade maior ou igual
    public static Specification<ProductModel> quantityGreaterThanOrEqual(Double minQuantity) {
        return (root, query, cb) ->
                minQuantity == null ? null : cb.greaterThanOrEqualTo(root.get("quantity"), minQuantity);
    }

    //Quantidade menor ou igual
    public static Specification<ProductModel> quantityLessThanOrEqual(Double maxQuantity) {
        return (root, query, cb) ->
                maxQuantity == null ? null : cb.lessThanOrEqualTo(root.get("quantity"), maxQuantity);
    }

    //Pertence ao usuario
    public static Specification<ProductModel> belongsToUser(Long userId) {
        return (root, query, cb) ->
                userId == null ? null : cb.equal(root.get("user").get("id"), userId);
    }
}
