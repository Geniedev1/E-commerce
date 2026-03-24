package com.example.demo.mapper;

import com.example.demo.dto.CartDTO;
import com.example.demo.model.Cart;

public class CartMapper {

    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId());
        return dto;
    }

    public static Cart toEntity(CartDTO dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUserId(dto.getUserId());
        return cart;
    }
}
