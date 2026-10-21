package github.gabrielbenini.EstoqueVivo.Product;

import github.gabrielbenini.EstoqueVivo.Category.Category;
import github.gabrielbenini.EstoqueVivo.Category.CategoryRepository;
import github.gabrielbenini.EstoqueVivo.Product.dtos.ProductRequest;
import github.gabrielbenini.EstoqueVivo.Product.dtos.ProductResponse;
import github.gabrielbenini.EstoqueVivo.commons.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductRequest productRequest){

        Category category = categoryRepository.findById(productRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria nao encontrada com o id " + id));

        Product product = productRequest.toEntity(category);
        productRepository.save(product);

        return ProductResponse.fromEntity(product);
    }

    public ProductResponse findProductById(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado com o id " + id));

        productRepository.save(product);
        return ProductResponse.fromEntity(product);
    }

    public List<ProductResponse> findAllProducts(){

        return productRepository.findAll()
                .stream()
                .map(ProductResponse::fromEntity)
                .toList();
    }

    public ProductResponse updateProductById(Long id, ProductRequest productRequest){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado com o id " + id));

        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria nao encontrada com o id " + id));

        productRequest.updateEntity(product, category);
        Product updatedProduct = productRepository.save(product);

        return ProductResponse.fromEntity(updatedProduct);
    }

    public void deleteProductById(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encontrado com o id " + id));

        productRepository.delete(product);
    }
}
