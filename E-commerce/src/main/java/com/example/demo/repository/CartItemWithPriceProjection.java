package com.example.demo.repository;

import org.springframework.stereotype.Component;

@Component
public interface CartItemWithPriceProjection {
    Long getId();

    Long getCartId();

    Long getProductId();

    int getQuantity();

    double getPrice();
}