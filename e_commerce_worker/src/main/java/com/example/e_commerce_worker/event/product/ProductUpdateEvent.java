package com.example.e_commerce_worker.event.product;

public class ProductUpdateEvent {
    private Long id;
    private double price;
    public ProductUpdateEvent() {
    }
    public ProductUpdateEvent(Long id, double price) {
        this.id = id;
        this.price = price;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
