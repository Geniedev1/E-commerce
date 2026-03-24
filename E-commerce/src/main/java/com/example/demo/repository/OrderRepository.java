package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    @Query("SELECT o.status FROM Order o WHERE o.id = :orderId")
    OrderStatus findStatusById(Long orderId);

    @Query("SELECT SUM(oi.quantity * oi.price) FROM OrderItem oi WHERE oi.orderId = :orderId")
    double calculateTotalAmountByOrderId(Long orderId);

    @Query(value = """
        SELECT o.*
        FROM Orders o
        JOIN OrderItem oi ON oi.orderId = o.id
        WHERE oi.id = :orderItemId
        """, nativeQuery = true)
    Order findOrderByOrderItemId(Long orderItemId);

}
