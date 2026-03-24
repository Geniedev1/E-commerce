package com.example.e_commerce_worker.event.product;

public class ProductDeleteEvent {
    private Long id;
    public ProductDeleteEvent(Long id) {
        this.id = id;   
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
