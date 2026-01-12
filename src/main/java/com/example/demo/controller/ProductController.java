package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProductService;
import com.example.demo.model.Product;
import com.example.demo.dto.ProductRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/addproduct")
    public Product addProduct(@RequestBody ProductRequest productRequest) { 
        return productService.addProduct(productRequest);
 }
    @GetMapping("/getAllProduct")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }
    
}
