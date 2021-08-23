package hu.dual.invoice.model;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity(name = "invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Invoice {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "comment")
    private String comment;

    @ElementCollection
    @OneToMany(mappedBy = "invoice")
    private Set<InvoiceItem> invoiceItems;

    @Column(name = "invoice_total")
    private BigDecimal invoiceTotal;
    
}
