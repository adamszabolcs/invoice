package hu.dual.invoice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import hu.dual.invoice.service.InvoiceItemService;
import hu.dual.invoice.service.InvoiceService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class WebController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    InvoiceItemService invoiceItemService;

    @GetMapping("/invoices")
    public List<Invoice> getInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllInvoice();
        return invoiceList;
    }

    @GetMapping("/invoice/{invoice-id}")
    public List<InvoiceItem> getInvoiceItems(@PathVariable("invoice-id") String invoiceId) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        List<InvoiceItem> invoiceItems = invoiceItemService.getAllInvoiceItemByInvoice(invoice);
        return invoiceItems;
    }

    @PostMapping("/invoice")
    public ResponseEntity<?> saveInvoice(@RequestBody String invoiceData) {
        ObjectMapper mapper = new ObjectMapper();
        Invoice invoice = null;
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
