package github.gabrielbenini.EstoqueVivo.StockMovement.dtos;

import github.gabrielbenini.EstoqueVivo.Product.Product;
import github.gabrielbenini.EstoqueVivo.StockMovement.StockMovement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record StockMovementRequest(
        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity must be greater than zero")
        Integer quantity,
        @NotBlank(message = "Reason cannot be blank")
        String reason,
        String notes,
        @NotNull(message = "ProductId cannot be blank")
        Long productId
) {

    public StockMovement toEntity(Product product){

        StockMovement stockMovement = new StockMovement();

        stockMovement.setQuantity(this.quantity);
        stockMovement.setReason(this.reason);
        stockMovement.setNotes(this.notes);
        stockMovement.setProduct(product);

        return stockMovement;
    }

    public void updateEntity(StockMovement stockMovement, Product product){
        stockMovement.setQuantity(this.quantity);
        stockMovement.setReason(this.reason);
        stockMovement.setNotes(this.notes);
        stockMovement.setCreatedAt(LocalDateTime.now());
        stockMovement.setProduct(product);
    }
}
