package com.example.demo.service;
import java.util.List;

import com.example.demo.dto.CartDTO;
import com.example.demo.model.CartItem;

public interface CartService {
    CartDTO getCart(Long userId, int page, int size);
    void addToCart(Long userId, Long productId, int quantity);
    void removeFromCart(Long userId, Long cartItemId);
    void clearCart(Long userId);
    void updateCartItemQuantity(Long userId, Long cartItemId, int quantity);
    List<CartItem> getCartItems(Long userId);
}
