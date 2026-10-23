package github.gabrielbenini.EstoqueVivo.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
 SELECT p
  FROM Product p
    WHERE p.active = true
      AND p.quantity <= p.minimumStock
       \s""")
    List<Product> findProductsWithLowStock();

    @Query("""
    SELECT p
    FROM Product p
    WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
      AND (:categoryId IS NULL OR p.category.id = :categoryId)
      AND (:active IS NULL OR p.active = :active)
""")
    Page<Product> findWithFilters(
            @Param("name") String name,
            @Param("categoryId") Long categoryId,
            @Param("active") Boolean active,
            Pageable pageable
    );

    @Query("""
    SELECT SUM(p.costPrice * p.quantity)
    FROM Product p
    WHERE p.active = true
""")
    BigDecimal calculateTotalStockValue();

}
