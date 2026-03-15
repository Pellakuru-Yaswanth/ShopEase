package com.shopease.repository.shopping;

import com.shopease.bean.shopping.Wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // 1. Get all wishlist items for a specific user
    // This powers the "My Wishlist" page
    List<Wishlist> findByUser_UserId(Long userId);

    // 2. Find a specific product in a user's wishlist
    // Used to check if a product is already "liked" or "saved"
    Optional<Wishlist> findByUser_UserIdAndProduct_ProductId(Long userId, Long productId);

    // 3. Remove a specific product from a user's wishlist
    // Used when a user clicks the "heart" icon again to unlike it
    void deleteByUser_UserIdAndProduct_ProductId(Long userId, Long productId);

    // 4. Count how many items are in a user's wishlist
    // Useful for showing a badge count (e.g., ❤️ 5) on the header
    long countByUser_UserId(Long userId);
}