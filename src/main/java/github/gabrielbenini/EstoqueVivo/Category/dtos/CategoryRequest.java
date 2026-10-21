package github.gabrielbenini.EstoqueVivo.Category.dtos;

import github.gabrielbenini.EstoqueVivo.Category.Category;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Description cannot be blank")
        String description
) {

    public Category toEntity(){

        Category category = new Category();

        category.setName(this.name);
        category.setDescription(this.description);

        return category;
    }

    public void updateEntity(Category category){
        category.setName(this.name);
        category.setDescription(this.description);
    }
}
