package hu.dual.invoice.service;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.Product;
import hu.dual.invoice.repository.InvoiceRepository;
import hu.dual.invoice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {

    @Autowired
    Invoice invoice;

    @Autowired
    Product product;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProductRepository productRepository;

}
