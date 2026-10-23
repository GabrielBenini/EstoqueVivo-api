package github.gabrielbenini.EstoqueVivo.Product;

import github.gabrielbenini.EstoqueVivo.Product.dtos.ProductRequest;
import github.gabrielbenini.EstoqueVivo.Product.dtos.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<ProductResponse>> findAllProducts(){

        List<ProductResponse> productResponse = productService.findAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
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
