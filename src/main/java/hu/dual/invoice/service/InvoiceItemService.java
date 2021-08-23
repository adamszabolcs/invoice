package hu.dual.invoice.service;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import hu.dual.invoice.model.Product;
import hu.dual.invoice.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class InvoiceItemService {

    @Autowired
    InvoiceItemRepository invoiceItemRepository;


    public InvoiceItem calculateTotalPriceAndSetToInvoiceItem(InvoiceItem item, Product product) {
        int quantity = item.getQuantity();
        BigDecimal unitPrice = product.getUnitPrice();
        BigDecimal totalUnitPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));

        item.setTotalPrice(totalUnitPrice);
        return item;
    }

    public List<InvoiceItem> getAllInvoiceItemByInvoice(Invoice invoice) {
        return invoiceItemRepository.findAllByInvoice(invoice);
    }

}
