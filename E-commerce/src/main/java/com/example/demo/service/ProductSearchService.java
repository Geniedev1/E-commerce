package com.example.demo.service;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.PageResponse;
public interface ProductSearchService {
   public PageResponse<ProductDTO> searchProducts(String productName,int page, int size);
}   