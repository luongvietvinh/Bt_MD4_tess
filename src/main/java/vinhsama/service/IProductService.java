package vinhsama.service;

import vinhsama.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public List<Product> findAll();
    public void save(Product product);
    public void delete(long id);
    public Product findById(long id);
}
