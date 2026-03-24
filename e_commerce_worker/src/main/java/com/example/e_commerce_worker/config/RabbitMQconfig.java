package com.example.e_commerce_worker.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
@Configuration
public class RabbitMQconfig {
    public static final String PRODUCT_EXCHANGE = "product_exchange";
    public static final String PRODUCT_ROUTING_KEY_ADD = "product_add";
    public static final String PRODUCT_ROUTING_KEY_UPDATE = "product_update";
    public static final String PRODUCT_ROUTING_KEY_DELETE = "product_delete";
    public static final String PRODUCT_QUEUE = "product_queue";
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(PRODUCT_EXCHANGE);
    }
    @Bean
    public Queue  queue() {
        return new Queue(PRODUCT_QUEUE);
    }
    @Bean
    public Binding bindingAdd(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(PRODUCT_ROUTING_KEY_ADD);
    }
    @Bean
    public Binding bindingUpdate(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(PRODUCT_ROUTING_KEY_UPDATE);
    }
    @Bean
    public Binding bindingDelete(Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(PRODUCT_ROUTING_KEY_DELETE);
    }
   
    }
    //đánh đấu bean để spring tạo ra bean này ngay khi khởi động ứng dụng vì muốn các class này phải tồn tại trong MQ ngay khi khởi động ứng dụng
    // ngay khi các class này tạo trong spring context thì nó sẽ tự động tạo ra các class này trong MQ nhờ amqp

