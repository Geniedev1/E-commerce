package com.example.e_commerce_worker.mapper;
import com.example.e_commerce_worker.document.product.ProductDocument;
import com.example.e_commerce_worker.event.product.ProductAddEvent;
import com.example.e_commerce_worker.event.product.ProductUpdateEvent;
public class ProductMapper {
    public static ProductDocument toProductDocument(ProductAddEvent event) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(String.valueOf(event.getId()));
        productDocument.setName(event.getName());
        productDocument.setPrice(event.getPrice());
        return productDocument;
    }
    
}