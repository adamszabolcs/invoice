package hu.dual.invoice.repository;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, String> {

    List<InvoiceItem> findAllByInvoice(Invoice invoice);
    List<InvoiceItem> findAllByInvoiceId(String invoiceId);

}
