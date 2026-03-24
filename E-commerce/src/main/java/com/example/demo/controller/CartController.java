package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CartDTO;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
        public ApiResponse<CartDTO> getCart(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        if (userDetails == null) {
             return new ApiResponse<>("error", "User not authenticated", null);
        }
        CartDTO cartDTO = cartService.getCart(userDetails.getId(), page, size);
        return new ApiResponse<>("success", "Cart retrieved successfully", cartDTO);
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> addToCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        
        if (userDetails == null) {
            return new ApiResponse<>("error", "User not authenticated", null);
        }

        cartService.addToCart(userDetails.getId(), productId, quantity);
        return new ApiResponse<>("success", "Item added to cart", null);
    }

    @DeleteMapping("/items/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponse<Void> removeFromCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cartItemId) {
        
        if (userDetails == null) {
            return new ApiResponse<>("error", "User not authenticated", null);
        }

        cartService.removeFromCart(userDetails.getId(), cartItemId);
        return new ApiResponse<>("success", "Item removed from cart", null);
    }

    @PutMapping("/items/{cartItemId}")
    public ApiResponse<Void> updateCartItem(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {
        
        if (userDetails == null) {
             return new ApiResponse<>("error", "User not authenticated", null);
        }

        cartService.updateCartItemQuantity(userDetails.getId(), cartItemId, quantity);
        return new ApiResponse<>("success", "Cart item updated", null);
    }

    @DeleteMapping
    public ApiResponse<Void> clearCart(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return new ApiResponse<>("error", "User not authenticated", null);
        }

        cartService.clearCart(userDetails.getId());
        return new ApiResponse<>("success", "Cart cleared", null);
    }
}
