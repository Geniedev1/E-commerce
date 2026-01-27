package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Order;
import com.example.demo.model.Role;
import com.example.demo.model.UserStatus;
import com.example.demo.service.OrderService;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.UserDTO;
@RestController
public class OrderController {
    OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }  
@PostMapping("/order")
public OrderDTO order(@RequestBody ProductDTO productDTO,@RequestParam int quantity) {
    //TODO: process POST request
    UserDTO userDTO = new UserDTO(1L, "John Doe", "john.doe@example.com",UserStatus.ACTIVE, "2024-06-01", Role.ADMIN);
   return  orderService.addItem(productDTO, quantity, userDTO);
    
  }
}
