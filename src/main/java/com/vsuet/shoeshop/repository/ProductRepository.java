package com.vsuet.shoeshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vsuet.shoeshop.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}