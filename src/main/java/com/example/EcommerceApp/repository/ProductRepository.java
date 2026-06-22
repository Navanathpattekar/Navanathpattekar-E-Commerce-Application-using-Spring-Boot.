package com.example.EcommerceApp.repository;

import com.example.EcommerceApp.model.Category;
import com.example.EcommerceApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Correct: category is String
    List<Product> findByCategory(Category category);

    // For seller dashboard
    List<Product> findBySellerId(Long sellerId);
    
    void deleteBySellerId(Long sellerId);

}
