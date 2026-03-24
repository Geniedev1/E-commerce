package com.example.demo.service;
import com.example.demo.event.product.ProductAddEvent;
import com.example.demo.event.product.ProductDeleteEvent;
import com.example.demo.event.product.ProductUpdateEvent;
public interface  ProductProducer {
   public void sendAddProduct( ProductAddEvent event);
   public void sendUpdateProduct(ProductUpdateEvent event);
    public void sendDeleteProduct(ProductDeleteEvent event);
}
