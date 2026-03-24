package com.example.e_commerce_worker.consumer.product;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import com.example.e_commerce_worker.config.RabbitMQconfig;
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
    public void receiveAddProduct(ProductAddEvent productEvent) {
        productService.handleAddProduct(productEvent);
    }
    @RabbitListener(queues = RabbitMQconfig.PRODUCT_QUEUE)  
    public void receiveUpdateProduct(ProductUpdateEvent productEvent) {
        productService.handleUpdateProduct(productEvent);
    }
    @RabbitListener(queues = RabbitMQconfig.PRODUCT_QUEUE)
    public void receiveDeleteProduct(ProductDeleteEvent productEvent) {
        productService.handleDeleteProduct(productEvent);
    }
}
