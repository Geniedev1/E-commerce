package com.example.demo.mapper;

import com.example.demo.dto.OrderItemDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.model.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setProductId(orderItem.getProductId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());   
        return orderItemDTO;
    }

    // public static OrderItem toEntity(OrderItemDTO orderItemDTO) {
    //     OrderItem orderItem = new OrderItem();
    //     orderItem.setId(orderItemDTO.getId());
        
    //     if (orderItemDTO.getProductId() != null) {
    //         orderItem.setProductId(orderItemDTO.getProductId());
    //     }
        
    //     orderItem.setQuantity(orderItemDTO.getQuantity());
    //     orderItem.setPrice(orderItemDTO.getPrice());
    //     return orderItem;
    // }
}
