package github.gabrielbenini.EstoqueVivo.StockMovement.dtos;

import github.gabrielbenini.EstoqueVivo.StockMovement.StockMovement;

import java.time.LocalDateTime;

public record StockMovementResponse(
        Long id,
        Integer quantity,
        String reason,
        String notes,
        LocalDateTime updatedAt,
        Long productId
) {

    public static StockMovementResponse fromEntity(StockMovement stockMovement){

        return new StockMovementResponse(
                stockMovement.getId(),
                stockMovement.getQuantity(),
                stockMovement.getReason(),
                stockMovement.getNotes(),
                stockMovement.getUpdatedAt(),
                stockMovement.getProduct().getId()
        );
    }
}
