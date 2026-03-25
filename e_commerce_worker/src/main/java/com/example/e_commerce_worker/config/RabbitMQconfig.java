package com.example.e_commerce_worker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//- explain: Ở đây là consumer nên phải cấu hình hầu exchange,queue, binding để khi producer đẩy message vào MQ thì MQ sẽ biết đẩy message vào đúng queue để consumer lấy message ra xử lý,
// nếu không cấu config exchange, queue, binding thì khi producer đẩy message vào MQ thì MQ sẽ không biết đẩy message vào đâu và consumer sẽ không lấy được message ra để xử lý
// Architecture: Đảm bảo consumer và producer không biết về nhau, chỉ biết về MQ, producer đẩy message vào MQ và consumer lấy message ra từ MQ để xử lý, như vậy sẽ đảm bảo tính loose coupling giữa producer và consumer, nếu sau này muốn thay đổi producer hoặc consumer thì chỉ cần thay đổi code trong producer hoặc consumer mà không ảnh hưởng đến bên còn lại, miễn là vẫn giữ nguyên cấu trúc message và routing key thì MQ vẫn sẽ đẩy message vào đúng queue để consumer lấy ra xử lý

@Configuration
public class RabbitMQconfig {

    // exhange :định nghĩa sự kiện thuộc vùng nào
    public static final String PRODUCT_EXCHANGE = "product_exchange";
    //routing key: định nghĩa trong vùng đó có chuyện gì
    public static final String PRODUCT_ROUTING_KEY_ADD = "product_add";
    public static final String PRODUCT_ROUTING_KEY_UPDATE = "product_update";
    public static final String PRODUCT_ROUTING_KEY_DELETE = "product_delete";
    public static final String PRODUCT_QUEUE = "product_queue";

    //Khi producer đẩy message vào MQ thì kiểu data luôn là byte[] dạng binary
    //  sau đó khi consumer lấy message ra dạng binary sẽ không hiểu và không convert về object jvm nên cần có một kiểu serializer và deserializer thống nhất để convert về object jvm
    //  khi lấy message ra từ MQ và convert về byte[] khi đẩy message vào MQ, ở đây  sẽ dùng Jackson2JsonMessageConverter để convert về json khi đẩy message vào MQ và convert về object jvm khi lấy message ra từ MQ,
    //  nói chung bản chất dùng json2 là để thống nhất kiểu serializer và deserializer để convert về object jvm khi lấy message ra từ MQ và convert về byte[] khi đẩy message vào MQ, nếu không có kiểu serializer và deserializer thống nhất thì sẽ không convert được về object jvm khi lấy message ra từ MQ và sẽ không hiểu được message đó là gì, ngoài ra  convert dạng json thay vì binary còn giúp debug message trong MQ queue
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //Exchange là nơi message vào đầu tiên + với routing key để định tuyến message vào đúng queue,
    //  ở đây dùng direct exchange để định tuyến message vào đúng queue dựa trên routing key
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(PRODUCT_EXCHANGE);
    }

    //Ở đây đang mặc định khi gọi sẽ tạo queue là product_queue trong MQ,
    //nếu sau này scale sẽ cần tạo nhiều queue hơn thì sẽ cần cấu config lại để tạo nhiều queue trong MQ
    @Bean
    public Queue queue() {
        return new Queue(PRODUCT_QUEUE);
    }

    //Binding là liên kết giữa exchange và queue để định tuyến message vào đúng queue dựa trên routing key,
    // ở đây sẽ tạo  binding để định tuyến message vào đúng 1 queue dựa trên routing key add, update, delete.
    // Vì không có nhiều queue trong MQ nên chỉ cần thêm truyền queue vào thì MQ sẽ mặc định lấy queue duy nhất trong đó để bind. Trong trường hợp nhiều hơn 1 queue thì cấu config lại để bind đúng queue trong MQ
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

    //đánh đấu bean để spring tạo ra bean này ngay khi khởi động ứng dụng vì muốn các class này phải tồn tại trong MQ ngay khi khởi động ứng dụng
    // ngay khi các class này tạo trong spring context thì nó sẽ tự động tạo ra các class này trong MQ nhờ amqp
}

