package hu.dual.invoice.model;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(
            name = "system-uuid",
            strategy = "uuid"
    )
    @Column()
    private String id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Column(name = "user_comment")
    private String userComment;

    @ElementCollection
    @OneToMany(mappedBy = "invoice", targetEntity = InvoiceItem.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<InvoiceItem> invoiceItems;

    @Column(name = "invoice_total")
    private BigDecimal invoiceTotal;
    
}
