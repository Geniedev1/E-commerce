package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.repository.CartItemWithPriceProjection;
import com.example.demo.model.CartItem;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);
    Page<CartItem> findByCartId(Long cartId, Pageable pageable);
    @Query(value = """
            SELECT c.id AS id,
                   c.cartId AS cartId,
                   c.productId AS productId,
                   c.quantity AS quantity,
                   p.price AS price
            FROM CartItem c
            JOIN Product p ON p.id = c.productId
            WHERE c.cartId = :cartId
            """,
            countQuery = "SELECT COUNT(*) FROM CartItem c WHERE c.cartId = :cartId",
            nativeQuery = true)
    Page<CartItemWithPriceProjection> findCartItemsWithPriceByCartId(@Param("cartId") Long cartId, Pageable pageable);

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
    int deleteByCartId(Long cartId);
    @Query("SELECT c FROM CartItem c WHERE c.cartId = :cartId AND c.productId = :productId")
    CartItem findByProductId(@Param("cartId") Long cartId,
                             @Param("productId") Long productId);
}
