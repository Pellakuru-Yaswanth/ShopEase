package com.shopease.repository.catalog;

import com.shopease.bean.catalog.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 1. Filter products by Category ID
    List<Product> findByCategory_CategoryId(Long categoryId);

    // 2. Search products by name (Search Bar feature)
    // "Containing" acts like the SQL LIKE %name%
    List<Product> findByNameContainingIgnoreCase(String name);

    // 3. Filter by price range (Price Slider feature)
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // 4. Find products that are in stock
    List<Product> findByStockQuantityGreaterThan(int quantity);

    // 5. Find featured products (e.g., specific category + in stock)
    List<Product> findByCategory_NameAndStockQuantityGreaterThan(String categoryName, int quantity);
}