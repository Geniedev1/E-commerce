package com.example.demo.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.OrderDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.OrderStatus;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.FinalPrice;
import com.example.demo.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final FinalPrice finalPrice;
    private final CartService cartService;
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, FinalPrice finalPrice, CartService cartService)  {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.finalPrice = finalPrice;
        this.cartService = cartService;
    }
    @Override
    public OrderDTO checkout(Long userId) {
        //   log.debug("Placing order for user: " + userId + " with products: " + productQuantities);
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.PENDING);
        Order savedOrder = orderRepository.save(order);
        List<OrderItem> items = cartService.getCartItems(userId)
        .stream()
        .map(item -> {
            OrderItem oi = new OrderItem();
            oi.setOrderId(savedOrder.getId());
            oi.setProductId(item.getProductId());
            oi.setQuantity(item.getQuantity());
            oi.setPrice(finalPrice.calculateFinalPrice(item.getProductId()));
            return oi;
        }).toList();

orderItemRepository.saveAll(items);
        cartService.clearCart(userId);
        return OrderMapper.toDTO(savedOrder); 
    }

    @Override
    public void cancelOrder(Long orderId) {
//   log.debug("Cancelling order with ID: " + orderId); 
        if (checkValidateOrder(orderId)) {
//    log.info("Order with ID: " + orderId + " has been cancelled.");
            orderItemRepository.deleteByOrderId(orderId);
            orderRepository.deleteById(orderId);
        }
    }

    @Override
    public void addOrderItem(Long orderId, Long productId, int quantity) {
        if (checkValidateOrder(orderId)) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(productId);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(finalPrice.calculateFinalPrice(productId));
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void removeOrderItem(Long orderItemId) {
        Order order = orderRepository.findOrderByOrderItemId(orderItemId);
        if (checkValidateOrder(order.getId())) {
        orderItemRepository.deleteById(orderItemId);
        }
    }

    private boolean checkValidateOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order with ID: " + orderId + " not found."));
        if (order.isPaid()) {
            throw new IllegalStateException("Cannot modify a paid order with ID: " + orderId);
        }
        return true;
     }
    }
