package vinhsama.service;

import org.springframework.beans.factory.annotation.Autowired;
import vinhsama.model.Product;
import vinhsama.repository.ProductRepo;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService{
    @Autowired
    ProductRepo productRepo;
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepo.findAll();
    }

    @Override
    public void save(Product product) {
    productRepo.save(product);
    }

    @Override
    public void delete(long id) {
    productRepo.deleteById(id);
    }

    @Override
    public Product findById(long id) {
        return productRepo.findById(id).get();
    }
}
