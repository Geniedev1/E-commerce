package com.example.demo.mapper;

import com.example.demo.dto.OrderDTO;
import com.example.demo.model.Order;

public class OrderMapper {
    public static Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        return order;
    }
     public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        return orderDTO;
    }
}
