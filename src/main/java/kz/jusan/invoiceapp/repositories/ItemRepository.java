package kz.jusan.invoiceapp.repositories;

import kz.jusan.invoiceapp.entities.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    List<Item> findItemsBy();
    Item findItemById (int id);
}
