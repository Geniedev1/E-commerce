package com.example.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CartItemDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CartItemMapper;
import com.example.demo.mapper.CartMapper;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartItemWithPriceProjection;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    @Override
    public CartDTO getCart(Long userId, int page, int size) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id: " + userId));
        Pageable pageable = PageRequest.of(page, size);
        Page<CartItemWithPriceProjection> pagedCartItems =
            cartItemRepository.findCartItemsWithPriceByCartId(cart.getId(), pageable);
        List<CartItemDTO> cartItemDTOs = pagedCartItems.getContent()
                .stream()
                .map(CartItemMapper::CartItemWithPriceProjectiontoDTO)
                .toList();
        CartDTO cartDTO = CartMapper.toDTO(cart);
        cartDTO.setItems(cartItemDTOs);
        cartDTO.setInforPage(pagedCartItems);
        return cartDTO;
    }
    @Override
    @Transactional
    public void addToCart(Long userId, Long productId, int quantity) {
        Cart cart = getOrCreateCart(userId);
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        CartItem cartItem = new CartItem(cart.getId(), productId, quantity);
        cartItemRepository.save(cartItem);
    }
    private Cart getOrCreateCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart(userId);
                    return cartRepository.save(newCart);
                });
    }
    @Override
    @Transactional
    public void removeFromCart(Long userId, Long cartItemId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user id: " + userId));
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + cartItemId));
        if (!cartItem.getCartId().equals(cart.getId())) {
            throw new ResourceNotFoundException("Cart item does not belong to user's cart");
        }
        cartItemRepository.delete(cartItem);
    }
    @Override
    @Transactional
    public void clearCart(Long userId)
    {
        Cart cart = cartRepository.findByUserId(userId).
                orElseThrow(()-> new ResourceNotFoundException("Not found cart with" + userId));
     int totalCartItemRemove = cartItemRepository.deleteByCartId(cart.getId());
     if(totalCartItemRemove == 0)
         throw new ResourceNotFoundException("Cart empty");
    }
    @Override
    @Transactional
    public void updateCartItemQuantity(Long userId,Long cartItemId,int quantity)
    {
        Cart cart = cartRepository.findByUserId(userId).
                orElseThrow(()-> new ResourceNotFoundException("cart not found with" + userId));
        CartItem cartItem = cartItemRepository.findById(cartItemId).
                orElseThrow(()-> new ResourceNotFoundException("cart item not found with" + cartItemId));
        if(!cartItem.getCartId().equals(cart.getId()))
        {
            throw new ResourceNotFoundException("cart item does not belong to user's cart");
        }
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }
    @Override
    public List<CartItem> getCartItems(Long userId)
    {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(()-> new ResourceNotFoundException("not found " + userId));
        return cartItemRepository.findByCartId(cart.getId());

    }
}