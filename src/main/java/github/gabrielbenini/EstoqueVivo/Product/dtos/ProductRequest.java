package github.gabrielbenini.EstoqueVivo.Product.dtos;

import github.gabrielbenini.EstoqueVivo.Category.Category;
import github.gabrielbenini.EstoqueVivo.Product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description,
        @NotBlank(message = "Sku cannot be blank")
        String sku,
        @NotBlank(message = "Barcode cannot be blank")
        String barcode,
        @NotNull(message = "Cost Price cannot be blank")
        BigDecimal costPrice,
        @NotNull(message = "Sale Price cannot be blank")
        BigDecimal salePrice,
        @NotNull(message = "Quantity cannot be blank")
        Integer quantity,
        @NotNull(message = "MinimumStock cannot be blank")
        Integer minimumStock,
        @NotNull(message = "Active cannot be blank")
        Boolean active,
        @NotNull(message = "categoryId cannot be blank")
        Long categoryId
) {

    public Product toEntity(Category category){

        Product product = new Product();

        product.setName(this.name);
        product.setDescription(this.description);
        product.setSku(this.sku);
        product.setBarcode(this.barcode);
        product.setCostPrice(this.costPrice);
        product.setSalePrice(this.salePrice);
        product.setQuantity(this.quantity);
        product.setMinimumStock(this.minimumStock);
        product.setActive(this.active);
        product.setCategory(category);

        return product;
    }

    public void updateEntity(Product product, Category category){
        product.setName(this.name);
        product.setDescription(this.description);
        product.setSku(this.sku);
        product.setBarcode(this.barcode);
        product.setCostPrice(this.costPrice);
        product.setSalePrice(this.salePrice);
        product.setQuantity(this.quantity);
        product.setMinimumStock(this.minimumStock);
        product.setActive(this.active);
        product.setUpdatedAt(LocalDateTime.now());
        product.setCategory(category);
    }
}
