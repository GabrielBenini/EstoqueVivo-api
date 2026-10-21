package github.gabrielbenini.EstoqueVivo.Category;

import github.gabrielbenini.EstoqueVivo.Category.dtos.CategoryRequest;
import github.gabrielbenini.EstoqueVivo.Category.dtos.CategoryResponse;
import github.gabrielbenini.EstoqueVivo.commons.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest categoryRequest){

        Category category = categoryRequest.toEntity();
        categoryRepository.save(category);
        return CategoryResponse.fromEntity(category);
    }

    public CategoryResponse findCategoryById(Long id){

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        return CategoryResponse.fromEntity(category);
    }

    public List<CategoryResponse> listAllCategories(){

        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::fromEntity)
                .toList();
    }

    public CategoryResponse updateCategoryById(Long id, CategoryRequest categoryRequest){

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        categoryRequest.updateEntity(category);
        Category updatedCategory = categoryRepository.save(category);

        return CategoryResponse.fromEntity(updatedCategory);
    }

    public void deleteCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        categoryRepository.delete(category);
    }
}
