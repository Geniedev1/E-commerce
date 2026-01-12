package com.example.demo.service;
import java.util.List;
import java.util.Map;

import com.example.demo.dto.OrderRequest;
import com.example.demo.model.Order;
public interface OrderService {
 public Order placeOrder(OrderRequest orderRequest);
 public void cancelOrder(String orderId);
 public Order getOrderDetails(String orderId);
} 
