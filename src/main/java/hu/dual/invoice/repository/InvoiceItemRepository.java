package hu.dual.invoice.repository;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, UUID> {

    List<InvoiceItem> findAllByInvoice(Invoice invoice);

}
