package com.example.EcommerceApp.service;

import com.example.EcommerceApp.model.Category;
import com.example.EcommerceApp.model.Product;
import com.example.EcommerceApp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    // ✅ SINGLE source of truth
    public void saveProduct(Product product, Long sellerId) {
        product.setSellerId(sellerId);

        // Normalize category before saving
        if (product.getCategory() != null) {
            try {
                // Convert incoming string to enum value
                Category categoryEnum = Category.valueOf(product.getCategory().name().toUpperCase());
                product.setCategory(categoryEnum);
            } catch (IllegalArgumentException e) {
                // fallback: set default category if needed
                product.setCategory(null); 
            }
        }

        repo.save(product);
    }


    public List<Product> getProductsByCategory(String categoryStr) {
        if (categoryStr == null || categoryStr.isBlank()) {
            return List.of();
        }

        // Convert string to enum
        Category category;
        try {
            category = Category.valueOf(categoryStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            // invalid category string
            return List.of();
        }

        return repo.findByCategory(category);
    }

    public List<Product> getProductsBySellerId(Long sellerId) {
        return repo.findBySellerId(sellerId);
    }

    public Product getProductById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteProduct(Long productId) {
        repo.deleteById(productId);
    }
    
    public List<Product> getSellerProducts(Long sellerId) {
        return repo.findBySellerId(sellerId);
    }
    
    public List<Product> getAllProducts() {
        return repo.findAll();
    }
    
    public void deleteProductById(Long id) {
        repo.deleteById(id);
    }


}
