package com.example.e_commerce_worker.consumer.product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.example.e_commerce_worker.config.RabbitMQconfig;
import com.example.e_commerce_worker.event.product.Event;
import com.example.e_commerce_worker.event.product.ProductAddEvent;
import com.example.e_commerce_worker.event.product.ProductDeleteEvent;
import com.example.e_commerce_worker.event.product.ProductUpdateEvent;
import com.example.e_commerce_worker.service.ProductService;
@Component
public class ConsumerProduct {
    private ProductService productService;
    public ConsumerProduct(ProductService productService) {
        this.productService = productService;
    }
    @RabbitListener(queues = RabbitMQconfig.PRODUCT_QUEUE)
    public void receive(Event event) {
        if (event instanceof ProductAddEvent) {
            ProductAddEvent addEvent = (ProductAddEvent) event;
            productService.handleAddProduct(addEvent);
        } else if (event instanceof ProductUpdateEvent) {
            ProductUpdateEvent updateEvent = (ProductUpdateEvent) event;
            productService.handleUpdateProduct(updateEvent);
        } else if (event instanceof ProductDeleteEvent) {
            ProductDeleteEvent deleteEvent = (ProductDeleteEvent) event;
            productService.handleDeleteProduct(deleteEvent);
        }
    }
   
}
