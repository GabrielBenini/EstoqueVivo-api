package github.gabrielbenini.EstoqueVivo.Category.dtos;

import github.gabrielbenini.EstoqueVivo.Category.Category;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {

    public static CategoryResponse fromEntity(Category category){

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getCreatedAt()
        );
    }
}
