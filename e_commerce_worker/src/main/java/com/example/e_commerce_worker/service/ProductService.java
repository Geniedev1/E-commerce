package com.example.e_commerce_worker.service;
import com.example.e_commerce_worker.event.product.ProductAddEvent;
import com.example.e_commerce_worker.event.product.ProductDeleteEvent;
import com.example.e_commerce_worker.event.product.ProductUpdateEvent;
public interface  ProductService {
   public void handleAddProduct( ProductAddEvent event);
   public void handleUpdateProduct(ProductUpdateEvent event);
   public void handleDeleteProduct(ProductDeleteEvent event);
}
