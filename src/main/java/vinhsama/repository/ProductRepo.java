package vinhsama.repository;

import org.springframework.data.repository.CrudRepository;
import vinhsama.model.Product;

public interface ProductRepo extends CrudRepository<Product ,Long> {
}
