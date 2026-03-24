package com.example.e_commerce_worker.service.impl;
import com.example.e_commerce_worker.document.product.ProductDocument;
import com.example.e_commerce_worker.event.product.ProductAddEvent;
import com.example.e_commerce_worker.event.product.ProductDeleteEvent;
import com.example.e_commerce_worker.event.product.ProductUpdateEvent;
import com.example.e_commerce_worker.mapper.ProductMapper;
import com.example.e_commerce_worker.service.ProductService;
import com.example.e_commerce_worker.reponsitory.ProductRepository;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public void handleAddProduct(ProductAddEvent event) {
        ProductDocument productDocument = ProductMapper.toProductDocument(event);
        productRepository.save(productDocument);
    }

    @Override
    public void handleUpdateProduct(ProductUpdateEvent event) {
        ProductDocument productDocument = productRepository.findById(String.valueOf(event.getId())).orElse(null);
        productDocument.setPrice(event.getPrice());
        productRepository.save(productDocument);
    }

    @Override
    public void handleDeleteProduct(ProductDeleteEvent event) {
        productRepository.deleteById(String.valueOf(event.getId()));
    }
    
}
