package com.vsuet.shoeshop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vsuet.shoeshop.model.Sale;

public interface SaleRepository extends MongoRepository<Sale, String> {
}