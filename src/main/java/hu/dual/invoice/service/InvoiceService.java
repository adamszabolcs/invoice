package hu.dual.invoice.service;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import hu.dual.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public BigDecimal calculateTotalPrice(Set<InvoiceItem> invoiceItemSet) {
        BigDecimal total = BigDecimal.ZERO;

        for (InvoiceItem item : invoiceItemSet) {
            total = total.add(item.getTotalPrice());
        }
        return total;
    }

    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findInvoiceById(UUID.fromString(id));
    }

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
