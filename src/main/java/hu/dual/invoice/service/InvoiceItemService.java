package hu.dual.invoice.service;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import hu.dual.invoice.model.Product;
import hu.dual.invoice.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public List<InvoiceItem> getAllInvoiceItemByInvoiceId(String invoiceId) {
        return invoiceItemRepository.findAllByInvoiceId(invoiceId);
    }

    public List<InvoiceItem> calculateTotalPriceInEUR(List<InvoiceItem> invoiceItemList) {
        for (InvoiceItem item : invoiceItemList) {
            item.setTotalPriceInEUR(item.getTotalPrice().divide(CurrencyExchangeService.exchangeRate, 2, RoundingMode.HALF_UP));
        }
        return invoiceItemList;
    }

}
