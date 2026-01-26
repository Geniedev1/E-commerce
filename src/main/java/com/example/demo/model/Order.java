package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.List;

import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(
        mappedBy = "order",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderItem> orderItems = new ArrayList<>();
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }   
    public void addOrderItems(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Order() {
    }   
    public Order(Long id, User user, List<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = orderItems;
        this.id = id;
    }
     public void removeOrderItems(OrderItem orderItem) {
        this.orderItems.remove(orderItem);
  }
  public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
  }
  
}