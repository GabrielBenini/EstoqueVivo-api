package github.gabrielbenini.EstoqueVivo.Stock;

import github.gabrielbenini.EstoqueVivo.Category.Category;
import github.gabrielbenini.EstoqueVivo.Product.Product;
import github.gabrielbenini.EstoqueVivo.StockMovement.StockMovement;
import github.gabrielbenini.EstoqueVivo.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "stock")
    private List<Category> categories;

    @OneToMany(mappedBy = "stock")
    private List<Product> products;

    @OneToMany(mappedBy = "stock")
    private List<StockMovement> stockMovements;

}
