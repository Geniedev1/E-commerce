package com.example.demo.service.impl;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.FirstOrderService;
@Service
public class FirstOrderServiceImpl implements FirstOrderService {
    private final OrderRepository orderRepository;
    public FirstOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Override
    public void firstOrder(User user) {
        Order order = new Order();
        order.setUserId(user.getId());
        orderRepository.save(order);
    }    
}
