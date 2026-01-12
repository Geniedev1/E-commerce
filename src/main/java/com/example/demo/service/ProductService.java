package com.example.demo.service;
import java.util.List;
import com.example.demo.model.Product;
import com.example.demo.dto.ProductRequest;

public interface ProductService {
    public void checkProduct(String productId);
    public Product addProduct(ProductRequest productRequest);
    public List<Product> getAllProducts();
}