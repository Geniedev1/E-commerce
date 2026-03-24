package com.example.demo.service;
import com.example.demo.dto.OrderDTO;
public interface OrderService {
 public OrderDTO checkout(Long userId);
 public void cancelOrder(Long orderId);
 public void addOrderItem(Long orderId, Long productId, int quantity);
 public void removeOrderItem(Long orderItemId);
} 
