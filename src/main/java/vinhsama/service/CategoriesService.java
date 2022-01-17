package vinhsama.service;

import org.springframework.beans.factory.annotation.Autowired;
import vinhsama.model.Categories;
import vinhsama.repository.CategoriesRepo;

import java.util.List;

public class CategoriesService implements ICategories{
    @Autowired
    CategoriesRepo categoriesRepo;
    @Override
    public List<Categories> findAll() {
        return (List<Categories>) categoriesRepo.findAll();
    }
}
