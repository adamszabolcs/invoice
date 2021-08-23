package hu.dual.invoice.repository;

import hu.dual.invoice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Product findProductById(UUID id);

}
