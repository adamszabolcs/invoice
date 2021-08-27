package hu.dual.invoice.repository;

import hu.dual.invoice.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, String> {

    List<InvoiceItem> findAllByInvoiceId(String invoiceId);

}
