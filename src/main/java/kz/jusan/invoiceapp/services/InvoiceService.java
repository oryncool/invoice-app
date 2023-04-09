package kz.jusan.invoiceapp.services;

import kz.jusan.invoiceapp.entities.Invoice;
import kz.jusan.invoiceapp.entities.Item;
import kz.jusan.invoiceapp.models.InvoiceRequest;
import kz.jusan.invoiceapp.models.Status;
import kz.jusan.invoiceapp.models.Statuses;
import kz.jusan.invoiceapp.repositories.AddressRepository;
import kz.jusan.invoiceapp.repositories.InvoiceRepository;
import kz.jusan.invoiceapp.repositories.ItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private AddressRepository addressRepository;
    private ItemRepository itemRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, AddressRepository addressRepository, ItemRepository itemRepository) {
        this.invoiceRepository = invoiceRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
    }

    public List<Invoice> getInvoices(Statuses statuses) {
        List<Invoice> invoices = invoiceRepository.findInvoicesByUserId(1);
        List<Invoice> copyInvoices = new ArrayList<>();
        for (int i = 0; i < invoices.size(); i++) {
            if (statuses.checkExistence(invoices.get(i).getStatus()))
                copyInvoices.add(invoices.get(i));
        }
        return copyInvoices;
    }

    public Invoice getInvoice(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            return invoice.get();
        }
        return null;
    }

    public String createInvoice(InvoiceRequest invoiceRequest) {
        Invoice invoice = new Invoice();
        invoice.setUserId(1);
        System.out.println(invoiceRequest.getCreatedAt());
        invoice.setCreatedAt(invoiceRequest.getCreatedAt());
        System.out.println(invoice.getCreatedAt());
        System.out.println(invoiceRequest.getPaymentDue());
        invoice.setPaymentDue(invoiceRequest.getPaymentDue());
        System.out.println(invoice.getPaymentDue());

        invoice.setDescription(invoiceRequest.getDescription());
        invoice.setPaymentTerms(invoiceRequest.getPaymentTerms());
        invoice.setClientName(invoiceRequest.getClientName());
        invoice.setClientEmail(invoiceRequest.getClientEmail());
        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setSenderAddress(invoiceRequest.getSenderAddress());
        invoice.setClientAddress(invoiceRequest.getClientAddress());
        invoice.setItems(invoiceRequest.getItems());
        double total = 0.0;
        for (Item item : invoiceRequest.getItems())
            total += item.getTotal();
        invoice.setTotal(total);
        invoiceRepository.save(invoice);
        return "Invoice was created";
    }

    public String deleteInvoice(int id) {
        invoiceRepository.deleteById(id);
        return "Invoice was deleted";
    }

    public String markAsPaid(int id) {
        Optional<Invoice> inv = invoiceRepository.findById(id);
        if (!inv.isPresent())
            return "Invoice was not found";
        Invoice invoice = inv.get();
        invoice.setStatus(Status.PAID);
        invoiceRepository.save(invoice);
        return "Invoice was marked as paid";
    }

    public String editInvoice(int id, InvoiceRequest invoiceRequest) {
        Optional<Invoice> inv = invoiceRepository.findById(id);
        if (!inv.isPresent())
            return "Invoice was not found";
        Invoice invoice = inv.get();
        invoice.setCreatedAt(invoiceRequest.getCreatedAt());
        invoice.setPaymentDue(invoiceRequest.getPaymentDue());
        invoice.setDescription(invoiceRequest.getDescription());
        invoice.setPaymentTerms(invoiceRequest.getPaymentTerms());
        invoice.setClientName(invoiceRequest.getClientName());
        invoice.setClientEmail(invoiceRequest.getClientEmail());
        invoice.setStatus(invoiceRequest.getStatus());
        if (invoice.getSenderAddress() == null)
            invoice.setSenderAddress(invoiceRequest.getSenderAddress());
        if (invoice.getClientAddress() == null)
        invoice.setClientAddress(invoiceRequest.getClientAddress());
        if (invoice.getItems() == null)
            invoice.setItems(invoiceRequest.getItems());
        double total = 0.0;
        for (Item item : invoiceRequest.getItems())
            total += item.getTotal();
        invoice.setTotal(total);
        invoiceRepository.save(invoice);
        return "Invoice was updated";
    }

}
