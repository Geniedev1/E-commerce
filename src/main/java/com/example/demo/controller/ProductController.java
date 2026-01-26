package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProductService;
import com.example.demo.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.ProductDTO;
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
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) { 
        return productService.addProduct(productDTO);
 }
    @GetMapping("/getAllProduct")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProducts();
    }
    
}
