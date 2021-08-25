package hu.dual.invoice.repository;

import hu.dual.invoice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Product findProductById(String id);

}
