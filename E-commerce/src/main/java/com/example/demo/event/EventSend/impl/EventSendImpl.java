package com.example.demo.event.EventSend.impl;
import com.example.demo.event.product.ProductAddEvent;
import com.example.demo.event.product.ProductDeleteEvent;
import com.example.demo.event.product.ProductUpdateEvent;
import com.example.demo.model.Product;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.ProductProducer;
import com.example.demo.event.EventSend.EventSend;
import org.springframework.stereotype.Component;
@Component
public class EventSendImpl implements EventSend {
    private ProductProducer productProducer;
      public EventSendImpl(ProductProducer productProducer) {
            this.productProducer = productProducer;
        }
    @Override
    public  void toAddProduct(Product product) {
        ProductAddEvent event = ProductMapper.toAddEvent(product);
        productProducer.sendAddProduct(event);
    }
    @Override
    public void toUpdateProduct(Product product) {
        ProductUpdateEvent event = ProductMapper.toUpdateEvent(product);
        productProducer.sendUpdateProduct(event);
    }
    @Override
    public void toDeleteProduct(Long productId) {
        ProductDeleteEvent event = ProductMapper.toDeleteEvent(productId);
        productProducer.sendDeleteProduct(event);
    }
}

