package com.example.demo.dto;
import java.util.Map;
import com.example.demo.model.User;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import java.util.List;
public class OrderDTO {
     private Long id;
     private Long userId;
     private double totalAmount;

     public OrderDTO (){
     }    
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
        public Long getUserId() {
            return userId;
        }
        public void setUserId(Long userId) {
            this.userId = userId;
        }
        public double getTotalAmount() {
            return totalAmount;
        }
        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }
}
