package github.gabrielbenini.EstoqueVivo.StockMovement;

import github.gabrielbenini.EstoqueVivo.StockMovement.dtos.StockMovementRequest;
import github.gabrielbenini.EstoqueVivo.StockMovement.dtos.StockMovementResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movements")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    @PostMapping
    public ResponseEntity<StockMovementResponse> createMovement(@RequestBody @Valid StockMovementRequest stockMovementRequest){

        StockMovementResponse stockMovementResponse = stockMovementService.createMovement(stockMovementRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(stockMovementResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponse> findMovement(@PathVariable("id") Long id){

        StockMovementResponse stockMovementResponse = stockMovementService.findMovement(id);
        return ResponseEntity.status(HttpStatus.OK).body(stockMovementResponse);
    }

    @GetMapping
    public ResponseEntity<List<StockMovementResponse>> findAllMovements(){
        List<StockMovementResponse> stockMovementResponse = stockMovementService.findAllMovements();
        return ResponseEntity.status(HttpStatus.OK).body(stockMovementResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockMovementResponse> updateMovement(@PathVariable("id")Long id, @RequestBody @Valid StockMovementRequest stockMovementRequest){

        StockMovementResponse stockMovementResponse = stockMovementService.updateMovement(id, stockMovementRequest);
        return ResponseEntity.status(HttpStatus.OK).body(stockMovementResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable("id") Long id){
        stockMovementService.deleteMovement(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
