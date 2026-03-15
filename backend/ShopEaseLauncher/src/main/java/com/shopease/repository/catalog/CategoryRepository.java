package com.shopease.repository.catalog;

import com.shopease.bean.catalog.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find a category by name (e.g., "Electronics")
    // Useful for breadcrumbs or category-specific landing pages
    Optional<Category> findByNameIgnoreCase(String name);

    // Check if a category name already exists (Useful for Admin Panel)
    boolean existsByNameIgnoreCase(String name);
}