package github.gabrielbenini.EstoqueVivo.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
 SELECT p
  FROM Product p
    WHERE p.active = true
      AND p.quantity <= p.minimumStock
       \s""")
    List<Product> findProductsWithLowStock();
}
