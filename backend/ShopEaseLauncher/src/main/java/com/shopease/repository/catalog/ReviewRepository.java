package com.shopease.repository.catalog;

import com.shopease.bean.catalog.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 1. Get all reviews for a specific product
    // Useful for the "Customer Reviews" section on a product page
    List<Review> findByProduct_ProductId(Long productId);

    // 2. Get all reviews written by a specific user
    // Useful for the "My Reviews" section in the user profile
    List<Review> findByUser_UserId(Long userId);

    // 3. Find reviews with a specific rating (e.g., all 5-star reviews)
    List<Review> findByProduct_ProductIdAndRating(Long productId, int rating);

    // 4. Check if a user has already reviewed a product
    // Useful to prevent duplicate reviews from the same person
    boolean existsByUser_UserIdAndProduct_ProductId(Long userId, Long productId);
}