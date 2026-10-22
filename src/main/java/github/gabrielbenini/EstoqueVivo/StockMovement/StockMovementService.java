package github.gabrielbenini.EstoqueVivo.StockMovement;

import github.gabrielbenini.EstoqueVivo.Product.Product;
import github.gabrielbenini.EstoqueVivo.Product.ProductRepository;
import github.gabrielbenini.EstoqueVivo.StockMovement.dtos.StockMovementRequest;
import github.gabrielbenini.EstoqueVivo.StockMovement.dtos.StockMovementResponse;
import github.gabrielbenini.EstoqueVivo.StockMovement.enums.MovementType;
import github.gabrielbenini.EstoqueVivo.commons.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    @Transactional
    public StockMovementResponse createMovement(StockMovementRequest stockMovementRequest){

        Product product = productRepository.findById(stockMovementRequest.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + stockMovementRequest.productId()));

        if (!Boolean.TRUE.equals(product.getActive())) {
            throw new IllegalArgumentException(
                    "Cannot Register movement for an inactive product"
            );
        }

        Integer previousQuantity = product.getQuantity();
        Integer quantity = stockMovementRequest.quantity();
        Integer resultingQuantity = previousQuantity + quantity;

        product.setQuantity(resultingQuantity);
        productRepository.save(product);

        StockMovement stockMovement = stockMovementRequest.toEntity(product);

        stockMovement.setType(MovementType.ENTRY);
        stockMovement.setPreviousQuantity(previousQuantity);
        stockMovement.setResultingQuantity(resultingQuantity);

        StockMovement savedMovement = stockMovementRepository.save(stockMovement);

        return StockMovementResponse.fromEntity(savedMovement);
    }

    public StockMovementResponse findMovement(Long id){

        StockMovement stockMovement = stockMovementRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Movement not found with this id"));

        stockMovementRepository.save(stockMovement);
        return StockMovementResponse.fromEntity(stockMovement);
    }

    public List<StockMovementResponse> findAllMovements(){

        return stockMovementRepository.findAll()
                .stream()
                .map(StockMovementResponse::fromEntity)
                .toList();
    }

    public StockMovementResponse updateMovement(Long id, StockMovementRequest stockMovementRequest){

        StockMovement stockMovement = stockMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found with this id"));

        Product product = productRepository.findById(stockMovementRequest.productId())
                        .orElseThrow(() -> new ResourceNotFoundException("Product not found with this id"));

        stockMovementRequest.updateEntity(stockMovement, product);
        StockMovement updatedMovement = stockMovementRepository.save(stockMovement);

        return StockMovementResponse.fromEntity(updatedMovement);

    }

    public void deleteMovement(Long id){

        StockMovement stockMovement = stockMovementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found with this id"));

        stockMovementRepository.delete(stockMovement);
    }



}
