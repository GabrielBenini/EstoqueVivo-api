package github.gabrielbenini.EstoqueVivo.Dashboard;

import github.gabrielbenini.EstoqueVivo.Product.ProductRepository;
import github.gabrielbenini.EstoqueVivo.StockMovement.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ProductRepository productRepository;
    private final StockMovementRepository stockMovementRepository;

    public long getTotalProducts() {
        return productRepository.count();
    }

    public long getLowStockProductCount(){
        return productRepository.findProductsWithLowStock()
                .size();
    }

    public long getTodayMovementsCount() {
        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return stockMovementRepository.countMovementsBetween(start, end);
    }

    public BigDecimal getTotalStockValue() {
        BigDecimal total = productRepository.calculateTotalStockValue();

        return total != null ? total : BigDecimal.ZERO;
    }

    public DashboardResponse getDashboard() {
        return new DashboardResponse(
                getTotalProducts(),
                getLowStockProductCount(),
                getTodayMovementsCount(),
                getTotalStockValue()
        );
    }

}
