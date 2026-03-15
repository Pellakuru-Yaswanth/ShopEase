package com.shopease.repository.shopping;

import com.shopease.bean.shopping.CartItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // 1. Get all items in a specific cart
    List<CartItem> findByCart_CartId(Long cartId);

    // 2. Find a specific product in a specific cart
    // Crucial for "Update Quantity" logic (e.g., if user adds the same item twice)
    Optional<CartItem> findByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);

    // 3. Clear all items from a cart
    // Used after a successful checkout
    void deleteByCart_CartId(Long cartId);
}