package kz.jusan.invoiceapp.repositories;

import kz.jusan.invoiceapp.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
    List<Invoice> findInvoicesByUserId(int id);

    Invoice findInvoiceById(Integer id);

    @Override
    Optional<Invoice> findById(Integer integer);
}
