package kz.jusan.invoiceapp.models;

import jakarta.persistence.*;
import kz.jusan.invoiceapp.entities.Address;
import kz.jusan.invoiceapp.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
public class InvoiceRequest {
    private Date createdAt;
    private Date paymentDue;
    private String description;
    private int paymentTerms;
    private String clientName;
    private String clientEmail;
    private Status status;
    private Address senderAddress;
    private Address clientAddress;
    private List<Item> items;
}
