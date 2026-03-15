package com.shopease.service;

import com.shopease.bean.catalog.Product;
import com.shopease.repository.catalog.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // 1. Fetch all products (for the main landing page)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 2. Search products by name
    public List<Product> searchProducts(String query) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }

    // 3. Filter products by category
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    // 4. Filter by price range
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    // 5. Get product details by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // 6. Admin: Add or Update Product
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    // 7. Admin: Delete Product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}