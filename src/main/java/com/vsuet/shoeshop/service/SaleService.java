package com.vsuet.shoeshop.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vsuet.shoeshop.exception.NotEnoughStockException;
import com.vsuet.shoeshop.model.Product;
import com.vsuet.shoeshop.model.Sale;
import com.vsuet.shoeshop.repository.ProductRepository;
import com.vsuet.shoeshop.repository.SaleRepository;

@Service
public class SaleService {

    private final SaleRepository sales;
    private final ProductRepository products;

    public SaleService(SaleRepository sales, ProductRepository products) {
        this.sales = sales;
        this.products = products;
    }

    public Sale createSale(String productId, int qty) {
        Product p = products.findById(productId).orElse(null);
        if (p == null) throw new RuntimeException("Product not found");

        if (p.getStock() < qty)
            throw new NotEnoughStockException("Not enough stock");

        p.setStock(p.getStock() - qty);
        products.save(p);

        Sale s = new Sale();
        s.setProductId(productId);
        s.setQuantity(qty);
        s.setTotalPrice(p.getPrice() * qty);
        s.setTimestamp(Instant.now());

        return sales.save(s);
    }

    public List<Sale> findAll() {
        return sales.findAll();
    }
}