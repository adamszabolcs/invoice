package hu.dual.invoice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import hu.dual.invoice.service.CurrencyExchangeService;
import hu.dual.invoice.service.InvoiceItemService;
import hu.dual.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class WebController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceItemService invoiceItemService;

    @GetMapping("/api/get-exchange-rate")
    public BigDecimal getExchangeRate() {
        return CurrencyExchangeService.exchangeRate;
    }


    @GetMapping("/api/invoices")
    public List<Invoice> getInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllInvoice();
        for (Invoice invoice : invoiceList){
            invoice.setInvoiceTotalInEUR(invoiceService.calculateTotalPriceInEUR(invoice));
            for (InvoiceItem item : invoice.getInvoiceItems()) {
                item.setTotalPriceInEUR(invoiceItemService.calculateInvoiceItemTotalPriceInEUR(item));
            }
        }
        return invoiceList;
    }

    @GetMapping("/api/invoice/{invoice-id}")
    public Invoice getInvoiceById(@PathVariable("invoice-id") String invoiceId) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        invoice.setInvoiceTotalInEUR(invoiceService.calculateTotalPriceInEUR(invoice));
        for (InvoiceItem item : invoice.getInvoiceItems()) {
            item.setTotalPriceInEUR(invoiceItemService.calculateInvoiceItemTotalPriceInEUR(item));
        }
        return invoice;
    }

    @GetMapping("/api/invoice-items/{invoice-id}")
    public List<InvoiceItem> getInvoiceItems(@PathVariable("invoice-id") String invoiceId) {
        List<InvoiceItem> invoiceItems = invoiceItemService.getAllInvoiceItemByInvoiceId(invoiceId);
        invoiceItems = invoiceItemService.calculateTotalPriceInEUR(invoiceItems);
        return invoiceItems;
    }

    @PostMapping("/invoice")
    public ResponseEntity<?> saveInvoice(@RequestBody String invoiceData) {
        ObjectMapper mapper = new ObjectMapper();
        Invoice invoice;
        try {
            JsonNode dataTree = mapper.readTree(invoiceData);
            invoice = mapper.treeToValue(dataTree.get("invoice"), Invoice.class);
            invoiceService.saveInvoice(invoice);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
