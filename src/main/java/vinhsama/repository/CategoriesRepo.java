package vinhsama.repository;

import org.springframework.data.repository.CrudRepository;
import vinhsama.model.Categories;

public interface CategoriesRepo extends CrudRepository<Categories , Long> {
}
