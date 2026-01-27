package com.example.demo.service.impservice;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FirstOrderService;
import com.example.demo.service.OrderService;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.resposity.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class IFirstOrderService implements FirstOrderService {
    private OrderRepository orderRepository;
    public IFirstOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public void firstOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);
    }    
}
