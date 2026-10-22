package github.gabrielbenini.EstoqueVivo.StockMovement.dtos;

import github.gabrielbenini.EstoqueVivo.Product.Product;
import github.gabrielbenini.EstoqueVivo.StockMovement.StockMovement;
import github.gabrielbenini.EstoqueVivo.StockMovement.enums.MovementType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record StockMovementRequest(
        @NotNull(message = "Type cannot be null")
        MovementType type,
        @NotNull(message = "Quantity cannot be null")
        Integer quantity,
        @NotBlank(message = "Reason cannot be blank")
        String reason,
        @NotBlank(message = "Notes cannot be blank")
        String notes,
        @NotNull(message = "ProductId cannot be blank")
        Long productId
) {

    public StockMovement toEntity(Product product){

        StockMovement stockMovement = new StockMovement();

        stockMovement.setType(this.type);
        stockMovement.setQuantity(this.quantity);
        stockMovement.setReason(this.reason);
        stockMovement.setNotes(this.notes);
        stockMovement.setProduct(product);

        return stockMovement;
    }

    public void updateEntity(StockMovement stockMovement, Product product){
        stockMovement.setType(this.type);
        stockMovement.setQuantity(this.quantity);
        stockMovement.setReason(this.reason);
        stockMovement.setNotes(this.notes);
        stockMovement.setUpdatedAt(LocalDateTime.now());
        stockMovement.setProduct(product);
    }
}
