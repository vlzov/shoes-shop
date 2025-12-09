package com.vsuet.shoeshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsuet.shoeshop.model.Sale;
import com.vsuet.shoeshop.service.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public Sale create(@RequestBody Map<String, Object> body) {
        String productId = (String) body.get("productId");
        int qty = (int) body.get("quantity");
        return service.createSale(productId, qty);
    }

    @GetMapping
    public List<Sale> list() {
        return service.findAll();
    }

    @DeleteMapping
    public ResponseEntity<Void> cleasrSales() {
        service.clearSales();
        return ResponseEntity.noContent().build();
    }
}