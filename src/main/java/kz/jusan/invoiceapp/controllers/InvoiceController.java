package kz.jusan.invoiceapp.controllers;

import jakarta.servlet.http.HttpServletRequest;
import kz.jusan.invoiceapp.entities.Invoice;
import kz.jusan.invoiceapp.entities.User;
import kz.jusan.invoiceapp.models.InvoiceRequest;
import kz.jusan.invoiceapp.models.Status;
import kz.jusan.invoiceapp.models.Statuses;
import kz.jusan.invoiceapp.security.JwtUtil;
import kz.jusan.invoiceapp.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private JwtUtil jwtUtil;

    private String getUsername(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            return null;
        }
        String jwtToken = token.substring(7);
        String username = jwtUtil.getUsernameFromToken(jwtToken);
        return username;
    }
    @GetMapping()
    public List<Invoice> getInvoices(@RequestBody Statuses statuses, HttpServletRequest request) {
        return invoiceService.getInvoices(statuses, getUsername(request));
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable int id, HttpServletRequest request) { return invoiceService.getInvoice(id, getUsername(request));}

    @PostMapping("/create")
    public Boolean createInvoice(@RequestBody InvoiceRequest invoiceRequest, HttpServletRequest request) {
        return invoiceService.createInvoice(invoiceRequest, getUsername(request));
    }

    @PostMapping("/{id}/delete")
    public Boolean deleteInvoice(@PathVariable int id, HttpServletRequest request) {
        return invoiceService.deleteInvoice(id, getUsername(request));
    }

    @PostMapping("/{id}/paid")
    public Boolean markAsPaid(@PathVariable int id, HttpServletRequest request) {
        return invoiceService.markAsPaid(id, getUsername(request));
    }

    @PostMapping("/{id}/edit")
    public Boolean editInvoice(@PathVariable int id, @RequestBody InvoiceRequest invoiceRequest, HttpServletRequest request) {
        return invoiceService.editInvoice(id, invoiceRequest, getUsername(request));
    }




}
