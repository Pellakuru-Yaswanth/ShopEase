package com.shopease.controller;

import com.shopease.bean.catalog.Product;
import com.shopease.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. GET: Fetch all products
    // Endpoint: http://localhost:8080/api/products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    // 2. GET: Fetch a single product by ID
    // Endpoint: http://localhost:8080/api/products/1
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null ? 
            new ResponseEntity<>(product, HttpStatus.OK) : 
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // 3. GET: Search products by name
    // Endpoint: http://localhost:8080/api/products/search?name=iphone
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        return new ResponseEntity<>(productService.searchProducts(name), HttpStatus.OK);
    }

    // 4. GET: Filter by Category
    // Endpoint: http://localhost:8080/api/products/category/2
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(productService.getProductsByCategory(categoryId), HttpStatus.OK);
    }

    // 5. POST: Add a new product (Admin feature)
    @PostMapping("/admin/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveOrUpdateProduct(product), HttpStatus.CREATED);
    }
}