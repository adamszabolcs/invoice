package hu.dual.invoice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/invoice/{invoiceId}")
    public String getInvoice(@PathVariable String invoiceId) {
        return "invoice";
    }
}
