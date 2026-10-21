package github.gabrielbenini.EstoqueVivo.Category;

import github.gabrielbenini.EstoqueVivo.Category.dtos.CategoryRequest;
import github.gabrielbenini.EstoqueVivo.Category.dtos.CategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){

        CategoryResponse categoryResponse = categoryService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable("id") Long id){

        CategoryResponse categoryResponse = categoryService.findCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listAllCategories(){

        List<CategoryResponse> categoryResponses = categoryService.listAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryById(@PathVariable("id") Long id, @RequestBody @Valid CategoryRequest categoryRequest){

        CategoryResponse categoryResponse = categoryService.updateCategoryById(id, categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long id){

        categoryService.deleteCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
