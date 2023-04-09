package kz.jusan.invoiceapp.controllers;

import kz.jusan.invoiceapp.entities.Invoice;
import kz.jusan.invoiceapp.models.InvoiceRequest;
import kz.jusan.invoiceapp.models.Status;
import kz.jusan.invoiceapp.models.Statuses;
import kz.jusan.invoiceapp.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping()
    public List<Invoice> getInvoices(@RequestBody Statuses statuses) {return invoiceService.getInvoices(statuses);}

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable int id) { return invoiceService.getInvoice(id);}

    @PostMapping("/create")
    public String createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.createInvoice(invoiceRequest);
    }

    @PostMapping("/{id}/delete")
    public String deleteInvoice(@PathVariable int id) {
        return invoiceService.deleteInvoice(id);
    }

    @PostMapping("/{id}/paid")
    public String markAsPaid(@PathVariable int id) {
        return invoiceService.markAsPaid(id);
    }

    @PostMapping("/{id}/edit")
    public String editInvoice(@PathVariable int id, @RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.editInvoice(id, invoiceRequest);
    }



}
