package com.example.demo.event.EventSend;
import com.example.demo.model.Product;
public interface EventSend {
    public void toAddProduct(Product product);
    public void toUpdateProduct(Product product);
    public void toDeleteProduct(Long productId);
}
