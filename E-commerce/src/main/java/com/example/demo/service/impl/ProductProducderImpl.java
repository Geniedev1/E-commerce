package com.example.demo.service.impl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.service.ProductProducer;
import org.springframework.stereotype.Service;
import com.example.demo.event.product.ProductAddEvent;
import com.example.demo.event.product.ProductDeleteEvent;
import com.example.demo.event.product.ProductUpdateEvent;
@Service
public class ProductProducderImpl implements ProductProducer {
    private RabbitTemplate rabbitTemplate;
    public ProductProducderImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void sendAddProduct(ProductAddEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.PRODUCT_EXCHANGE, RabbitMQConfig.PRODUCT_ROUTING_KEY_ADD, event);   
    }
    @Override
    public void sendUpdateProduct(ProductUpdateEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.PRODUCT_EXCHANGE, RabbitMQConfig.PRODUCT_ROUTING_KEY_UPDATE, event);   
    }
    @Override
    public void sendDeleteProduct(ProductDeleteEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.PRODUCT_EXCHANGE, RabbitMQConfig.PRODUCT_ROUTING_KEY_DELETE, event);   
    }
}
