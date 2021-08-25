package hu.dual.invoice.service;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import hu.dual.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public BigDecimal calculateTotalPrice(List<InvoiceItem> invoiceItemList) {
        BigDecimal total = BigDecimal.ZERO;

        for (InvoiceItem item : invoiceItemList) {
            total = total.add(item.getTotalPrice());
        }
        return total;
    }

    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findInvoiceById(id);
    }

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    public List<Invoice> calculateTotalPriceInEUR(List<Invoice> invoiceList) {
        for (Invoice invoice : invoiceList) {
            invoice.setInvoiceTotalInEUR(invoice.getInvoiceTotal().divide(CurrencyExchangeService.exchangeRate, 2, RoundingMode.HALF_UP));
            for (InvoiceItem item : invoice.getInvoiceItems()) {
                item.setTotalPriceInEUR(item.getTotalPrice().divide(CurrencyExchangeService.exchangeRate, 2, RoundingMode.HALF_UP));
            }
        }
        return invoiceList;
    }

}
