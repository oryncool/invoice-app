package kz.jusan.invoiceapp.services;

import kz.jusan.invoiceapp.entities.Invoice;
import kz.jusan.invoiceapp.entities.Item;
import kz.jusan.invoiceapp.entities.User;
import kz.jusan.invoiceapp.models.InvoiceRequest;
import kz.jusan.invoiceapp.models.Status;
import kz.jusan.invoiceapp.models.Statuses;
import kz.jusan.invoiceapp.repositories.AddressRepository;
import kz.jusan.invoiceapp.repositories.InvoiceRepository;
import kz.jusan.invoiceapp.repositories.ItemRepository;
import kz.jusan.invoiceapp.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@NoArgsConstructor
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private AddressRepository addressRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, AddressRepository addressRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.addressRepository = addressRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    private int getUserId(String username) {
        Optional<User> user = this.userRepository.findUserByUsername(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException("user" + username + " is not found");
        return user.get().getId();
    }


    public List<Invoice> getInvoices(Statuses statuses, String username) {
        List<Invoice> invoices = invoiceRepository.findInvoicesByUserId(getUserId(username));
        List<Invoice> copyInvoices = new ArrayList<>();
        for (int i = 0; i < invoices.size(); i++) {
            if (statuses.checkExistence(invoices.get(i).getStatus()))
                copyInvoices.add(invoices.get(i));
        }
        return copyInvoices;
    }

    public Invoice getInvoice(int id, String username) {
        Optional<Invoice> invoice = invoiceRepository.findInvoiceByIncrementIdAndUserId(id, getUserId(username));
        if (invoice.isPresent()) {
            return invoice.get();
        }
        return null;
    }

    public Boolean createInvoice(InvoiceRequest invoiceRequest, String username) {
        Invoice invoice = new Invoice();
        invoice.setUserId(getUserId(username));
        invoice.setId(generateCustomId(invoiceRepository.findInvoicesByUserId(1)));
        invoice.setCreatedAt(invoiceRequest.getCreatedAt());
        invoice.setPaymentDue(invoiceRequest.getPaymentDue());
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
        return true;
    }

    public Boolean deleteInvoice(int id, String username) {
        Optional<Invoice> invoice = invoiceRepository.findInvoiceByIncrementIdAndUserId(id, getUserId(username));
        if (!invoice.isPresent())
            return false;
        invoiceRepository.deleteById(id);
        return true;
    }

    public Boolean markAsPaid(int id, String username) {
        Optional<Invoice> inv = invoiceRepository.findInvoiceByIncrementIdAndUserId(id, getUserId(username));
        if (!inv.isPresent())
            return false;
        Invoice invoice = inv.get();
        invoice.setStatus(Status.PAID);
        invoiceRepository.save(invoice);
        return true;
    }

    public Boolean editInvoice(int id, InvoiceRequest invoiceRequest, String username) {
        Optional<Invoice> inv = invoiceRepository.findInvoiceByIncrementIdAndUserId(id, getUserId(username));
        if (!inv.isPresent())
            return false;
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
        return true;
    }

    public String generateCustomId(List<Invoice> invoices) {
        StringBuilder sb= new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";;
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        for (int i = 0; i < 4; i++) {
            int num = random.nextInt(9);
            sb.append(num);
        }
        String id = sb.toString();
        for (Invoice invoice : invoices) {
            if (invoice.getId().equals(id))
                return generateCustomId(invoices);
        }
        return id;
    }

}
