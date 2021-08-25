package hu.dual.invoice;

import hu.dual.invoice.model.Invoice;
import hu.dual.invoice.model.InvoiceItem;
import hu.dual.invoice.model.Product;
import hu.dual.invoice.repository.InvoiceRepository;
import hu.dual.invoice.repository.ProductRepository;
import hu.dual.invoice.service.CurrencyExchangeService;
import hu.dual.invoice.service.InvoiceItemService;
import hu.dual.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemService invoiceItemService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CurrencyExchangeService currencyExchangeService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Product firstProduct = Product.builder()
                    .name("First Product")
                    .unitPrice(BigDecimal.valueOf(1200))
                    .build();
            Product secondProduct = Product.builder().name("Second Product").unitPrice(BigDecimal.valueOf(4299)).build();

            productRepository.save(firstProduct);
            productRepository.save(secondProduct);

            InvoiceItem firstInvoiceItem = InvoiceItem.builder().product(firstProduct).quantity(2).build();
            firstInvoiceItem = invoiceItemService.calculateTotalPriceAndSetToInvoiceItem(firstInvoiceItem, firstProduct);
            InvoiceItem secondInvoiceItem = InvoiceItem.builder().product(secondProduct).quantity(4).build();
            secondInvoiceItem = invoiceItemService.calculateTotalPriceAndSetToInvoiceItem(secondInvoiceItem, secondProduct);

            List<InvoiceItem> invoiceItemSet = new ArrayList<>();
            invoiceItemSet.add(firstInvoiceItem);
            invoiceItemSet.add(secondInvoiceItem);
            Invoice invoice = Invoice.builder()
                    .customerName("Customer name")
                    .issueDate(new Date())
                    .dueDate(new Date())
                    .invoiceItems(invoiceItemSet)
                    .invoiceTotal(invoiceService.calculateTotalPrice(invoiceItemSet)).build();
            firstInvoiceItem.setInvoice(invoice);
            secondInvoiceItem.setInvoice(invoice);
            invoiceRepository.save(invoice);

            currencyExchangeService.getActualExchangeRate();
        };
    }
}
