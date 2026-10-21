package github.gabrielbenini.EstoqueVivo.StockMovement.dtos;

import github.gabrielbenini.EstoqueVivo.StockMovement.StockMovement;

import java.time.LocalDateTime;

public record StockMovementResponse(
        Long id,
        Integer quantity,
        Integer previousQuantity,
        Integer resultingQuantity,
        String reason,
        String notes,
        LocalDateTime createdAt,
        Long productId
) {

    public static StockMovementResponse fromEntity(StockMovement stockMovement){

        return new StockMovementResponse(
                stockMovement.getId(),
                stockMovement.getQuantity(),
                stockMovement.getPreviousQuantity(),
                stockMovement.getResultingQuantity(),
                stockMovement.getReason(),
                stockMovement.getNotes(),
                stockMovement.getCreatedAt(),
                stockMovement.getProduct().getId()
        );
    }
}
