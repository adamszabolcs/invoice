package hu.dual.invoice.service;

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


    public BigDecimal calculateTotalPriceOfInvoiceItem(InvoiceItem item, Product product) {
        int quantity = item.getQuantity();
        BigDecimal unitPrice = product.getUnitPrice();
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public List<InvoiceItem> getAllInvoiceItemByInvoiceId(String invoiceId) {
        return invoiceItemRepository.findAllByInvoiceId(invoiceId);
    }

    public List<InvoiceItem> calculateTotalPriceInEUR(List<InvoiceItem> invoiceItemList) {
        for (InvoiceItem item : invoiceItemList) {
            item.setTotalPriceInEUR(calculateInvoiceItemTotalPriceInEUR(item));
        }
        return invoiceItemList;
    }

    public BigDecimal calculateInvoiceItemTotalPriceInEUR(InvoiceItem invoiceItem) {
        return invoiceItem.getTotalPrice().divide(CurrencyExchangeService.exchangeRate, 2, RoundingMode.HALF_UP);
    }

}
