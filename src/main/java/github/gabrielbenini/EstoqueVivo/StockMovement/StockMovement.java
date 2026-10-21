package github.gabrielbenini.EstoqueVivo.StockMovement;

import github.gabrielbenini.EstoqueVivo.Product.Product;
import github.gabrielbenini.EstoqueVivo.StockMovement.enums.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MovementType type;

    private Integer quantity;

    @Column(name = "previous_quantity")
    private Integer previousQuantity;

    @Column(name = "resulting_quantity")
    private Integer resultingQuantity;

    private String reason;

    private String notes;

    @Column(name = "create_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
