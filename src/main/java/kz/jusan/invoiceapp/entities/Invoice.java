package kz.jusan.invoiceapp.entities;

import jakarta.persistence.*;
import kz.jusan.invoiceapp.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="invoices")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice {
    @Id
    @Column(name="increment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int incrementId;
    @Column(name="id")
    private String id;
    @Column(name="user_id")
    private int userId;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="payment_due")
    private Date paymentDue;
    @Column(name="description")
    private String description;
    @Column(name="payment_terms")
    private int paymentTerms;
    @Column(name="client_name")
    private String clientName;
    @Column(name="client_email")
    private String clientEmail;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sender_id", referencedColumnName = "id")
    private Address senderAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="client_id", referencedColumnName = "id")
    private Address clientAddress;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    @Column(name="total")
    private double total;




}
