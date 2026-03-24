package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.OrderDTO;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/items")
    public ApiResponse<Void> addOrderItem(@PathVariable Long orderId, 
                                          @RequestParam Long productId, 
                                          @RequestParam int quantity,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
         orderService.addOrderItem(orderId, productId, quantity);
         return new ApiResponse<>("success", "Order item added successfully", null);
    }

    @DeleteMapping("/items/{orderItemId}")
    public ApiResponse<Void> removeOrderItem(@PathVariable Long orderItemId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        orderService.removeOrderItem(orderItemId);
        return new ApiResponse<>("success", "Order item removed successfully", null);
    }

    @PostMapping("/checkout")
    public ApiResponse<OrderDTO> checkout(@AuthenticationPrincipal UserDetailsImpl userDetails) {
         OrderDTO orderDTO = orderService.checkout(userDetails.getId());
         return new ApiResponse<>("success", "Order placed successfully", orderDTO);
    }

    @PostMapping("/cancel")
    public ApiResponse<Void> cancelOrder(@AuthenticationPrincipal UserDetailsImpl userDetails) {
         orderService.cancelOrder(userDetails.getId());
         return new ApiResponse<>("success", "Order cancelled successfully", null);
    }
}