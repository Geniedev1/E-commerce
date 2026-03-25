package com.example.demo.service.impl;
import com.example.demo.service.FinalPrice;
import com.example.demo.service.ProductService;
public class FinalPriceImpl implements FinalPrice {
    private final ProductService productService;
    public FinalPriceImpl(ProductService productService) {
        this.productService = productService;
    }
    @Override
    public double calculateFinalPrice(Long productId) {
      return productService.getPrice(productId);
    }
    
}
