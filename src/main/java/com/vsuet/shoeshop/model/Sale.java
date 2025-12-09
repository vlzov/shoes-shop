package com.vsuet.shoeshop.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("sales")
public class Sale {
    @Id
    private String id;
    private String productId;
    private int quantity;
    private double totalPrice;
    private Instant timestamp;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}