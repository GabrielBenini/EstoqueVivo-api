package github.gabrielbenini.EstoqueVivo.StockMovement;

import github.gabrielbenini.EstoqueVivo.StockMovement.dtos.StockMovementResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    List<StockMovement> findAllByProductId(Long productId);

    @Query("""
    SELECT COUNT(smov)
    FROM StockMovement smov
    WHERE smov.createdAt >= :start
      AND smov.createdAt < :end
""")
    long countMovementsBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
