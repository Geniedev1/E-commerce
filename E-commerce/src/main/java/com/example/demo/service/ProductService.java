package com.example.demo.service;
import com.example.demo.dto.PageResponse;
import com.example.demo.dto.ProductDTO;
public interface ProductService {
    public ProductDTO addProduct(ProductDTO productDTO);
    public PageResponse<ProductDTO> getAllProducts(int page, int size);
    public double getPrice(Long productId);
    public boolean existsById(Long productId);
    public PageResponse<ProductDTO> searchProducts(String keyword, int page, int size);
    public void removeProduct(Long productId);
    public ProductDTO updateProduct(ProductDTO productDTO);
}