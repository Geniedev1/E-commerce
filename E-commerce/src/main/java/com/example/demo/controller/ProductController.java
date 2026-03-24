package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.addProduct(productDTO);
        return new ApiResponse<>("success", "Product added successfully", createdProduct);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PageResponse<ProductDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponse<ProductDTO> products = productService.getAllProducts(page, size);
        return new ApiResponse<>("success", "Products retrieved successfully", products);
    }
   @DeleteMapping
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void removeProduct(@RequestParam Long productId) {
        productService.removeProduct(productId);
   }
   @PatchMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        productDTO.setId(productId);
        ProductDTO updatedProduct = productService.updateProduct(productDTO);
        return new ApiResponse<>("success", "Product updated successfully", updatedProduct);
    }
    
}