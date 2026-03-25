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

    private final ProductService productService;

    public ConsumerProduct(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = RabbitMQconfig.PRODUCT_QUEUE)
    public void receive(Event event) {
        if (event instanceof ProductAddEvent) {
            productService.handleAddProduct((ProductAddEvent) event);
            return;
        }
        if (event instanceof ProductUpdateEvent) {
            productService.handleUpdateProduct((ProductUpdateEvent) event);
            return;
        }
        if (event instanceof ProductDeleteEvent) {
            productService.handleDeleteProduct((ProductDeleteEvent) event);
            return;
        }

        throw new IllegalArgumentException("Unsupported event type: " + event.getClass().getName());
    }
}
