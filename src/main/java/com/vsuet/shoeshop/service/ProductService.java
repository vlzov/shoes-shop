package com.vsuet.shoeshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vsuet.shoeshop.model.Product;
import com.vsuet.shoeshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(Product product) {
        return repo.save(product);
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product findById(String id) {
        return repo.findById(id).orElse(null);
    }

    public void reduceStock(String id, int quantity) {
        Product p = findById(id);
        if (p == null) return;
        p.setStock(p.getStock() - quantity);
        repo.save(p);
    }

    public void deleteProduct(String id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Товар не найден");
        }
        repo.deleteById(id);
    }

}