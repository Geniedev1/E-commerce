package com.example.demo.model;

import java.util.HashMap;

public class Order {
    private String orderId;
    private String userId;
    private User  user;
    private HashMap<String, Integer> products; // productId -> quantity
    private HashMap<Product,Integer> productList; // Product -> quantity
    public Order(String orderId, String userId, HashMap<String, Integer> products) {
        this.orderId = orderId;
        this.userId = userId;
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }
    public HashMap<String, Integer> getProducts() {
        return products;
 
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
    
}