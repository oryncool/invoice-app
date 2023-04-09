package kz.jusan.invoiceapp.repositories;

import kz.jusan.invoiceapp.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
