package github.gabrielbenini.EstoqueVivo.Product;

import github.gabrielbenini.EstoqueVivo.Product.dtos.ProductRequest;
import github.gabrielbenini.EstoqueVivo.Product.dtos.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest){

        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("id") Long id){

        ProductResponse productResponse = productService.findProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean active,
            Pageable pageable
    ) {
        Page<ProductResponse> products =
                productService.findWithFilters(name, categoryId, active, pageable);

        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable("id") Long id, @Valid @RequestBody ProductRequest productRequest){

        ProductResponse productResponse = productService.updateProductById(id, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("id") Long id){

        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<ProductResponse>> findProductsWithLowStock(){

        List<ProductResponse> productResponses = productService.findProductsWithLowStock();
        return ResponseEntity.status(HttpStatus.OK).body(productResponses);
    }

}
