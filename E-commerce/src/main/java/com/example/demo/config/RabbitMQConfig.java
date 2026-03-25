package com.example.demo.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//- Architecture: Đây là producer nó chỉ quan tâm đẩy message vào exchange nào (PRODUCT_EXCHANGE) với loại routing key nào không 
// quan tâm là đưa cho ai chỉ biết là đưa loại message này (routing key ) thuộc vùng này(EXCHANGE) vào MQ
@Configuration
public class RabbitMQConfig {
    public static final String PRODUCT_EXCHANGE = "product_exchange";
    public static final String PRODUCT_ROUTING_KEY_ADD = "product_add";
    public static final String PRODUCT_ROUTING_KEY_UPDATE = "product_update";
    public static final String PRODUCT_ROUTING_KEY_DELETE = "product_delete";
     @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
