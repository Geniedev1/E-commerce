package com.example.demo.mapper;
import com.example.demo.repository.CartItemWithPriceProjection;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.model.CartItem;
public class CartItemMapper {

    public static CartItemDTO toDTO(CartItem cartItem) {
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setCartId(cartItem.getCartId());
        dto.setProductId(cartItem.getProductId());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

    public static CartItem toEntity(CartItemDTO dto) {
        CartItem cartItem = new CartItem();
        cartItem.setId(dto.getId());
        cartItem.setCartId(dto.getCartId());
        cartItem.setProductId(dto.getProductId());
        cartItem.setQuantity(dto.getQuantity());
        return cartItem;
    }
    public static CartItemDTO CartItemWithPriceProjectiontoDTO(CartItemWithPriceProjection cartItemWithPriceProjection)
    {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItemWithPriceProjection.getId());
        cartItemDTO.setCartId(cartItemWithPriceProjection.getCartId());
        cartItemDTO.setProductId(cartItemWithPriceProjection.getProductId());
        cartItemDTO.setQuantity(cartItemWithPriceProjection.getQuantity());
        cartItemDTO.setPrice(cartItemWithPriceProjection.getPrice());
        return cartItemDTO;
    }
}
