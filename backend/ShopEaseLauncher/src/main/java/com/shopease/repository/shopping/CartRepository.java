package com.shopease.repository.shopping;

import com.shopease.bean.shopping.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // 1. Find the cart belonging to a specific user
    // This is the most used method in the shopping flow
    Optional<Cart> findByUser_UserId(Long userId);

    // 2. Find a cart by the user's email
    // Useful for fetching a cart directly during a login session
    Optional<Cart> findByUser_Email(String email);
    
    // 3. Check if a user already has an active cart
    boolean existsByUser_UserId(Long userId);
}