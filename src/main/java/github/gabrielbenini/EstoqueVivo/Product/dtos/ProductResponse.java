package github.gabrielbenini.EstoqueVivo.Product.dtos;

import github.gabrielbenini.EstoqueVivo.Category.Category;
import github.gabrielbenini.EstoqueVivo.Product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String description,
        String sku,
        String barcode,
        BigDecimal costPrice,
        BigDecimal salePrice,
        Integer quantity,
        Integer minimumStock,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long categoryId
) {

    public static ProductResponse fromEntity(Product product){

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getSku(),
                product.getBarcode(),
                product.getCostPrice(),
                product.getSalePrice(),
                product.getQuantity(),
                product.getMinimumStock(),
                product.getActive(),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getCategory().getId()
        );
    }
}
