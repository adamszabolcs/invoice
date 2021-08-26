package hu.dual.invoice.repository;

import hu.dual.invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    Invoice findInvoiceById(String id);

}
