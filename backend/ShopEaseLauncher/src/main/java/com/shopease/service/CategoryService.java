package com.shopease.service;

import com.shopease.bean.catalog.Category;
import com.shopease.repository.catalog.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. Fetch all categories for the navigation menu
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 2. Get a category by its ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // 3. Find category by name (useful for URL slugs or search)
    public Category getCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).orElse(null);
    }

    // 4. Admin: Create a new category
    public Category createCategory(Category category) throws Exception {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new Exception("Category with this name already exists!");
        }
        return categoryRepository.save(category);
    }

    // 5. Admin: Delete category
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}