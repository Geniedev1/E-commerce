package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import com.example.demo.dto.OrderRequest;
@RestController
public class OrderController {
    OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }  
@PostMapping("/order")
public Order order(@RequestBody OrderRequest orderRequest) {
    //TODO: process POST request
   return  orderService.placeOrder(orderRequest);
    
  }
}
