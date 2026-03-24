package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderItem;
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
   @Query("SELECT SUM(oi.quantity * oi.price) FROM OrderItem oi WHERE oi.orderId = :orderId")
   double calculateTotalAmountByOrderId(Long orderId);
   @Query("DELETE FROM OrderItem oi WHERE oi.orderId = :orderId")
   void deleteByOrderId(Long orderId);
   @Query("SELECT oi FROM OrderItem oi WHERE oi.orderId = :orderId")
   List<OrderItem> findByOrderId(Long orderId);
} 
