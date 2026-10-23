package github.gabrielbenini.EstoqueVivo.Dashboard;

import java.math.BigDecimal;

public record DashboardResponse(
        long totalProducts,
        long lowStockProductsCount,
        long todayMovementsCount,
        BigDecimal totalStockValue
) {}
