package com.example.EcommerceApp.Controller;

import com.example.EcommerceApp.model.Product;
import com.example.EcommerceApp.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private ProductService productService;

    // -----------------------------
    // Seller Dashboard
    // -----------------------------
    @GetMapping("/dashboard")
    public String sellerDashboard(Model model, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("userId");
        if (sellerId == null) {
            return "redirect:/login";  // Seller not logged in
        }

        // Fetch all products for this seller
        List<Product> products = productService.getProductsBySellerId(sellerId);
        model.addAttribute("products", products);

        return "seller-dashboard";
    }

    // -----------------------------
    // Show Add Product Form
    // -----------------------------
    @GetMapping("/add-product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "seller-add-product";
    }

    // -----------------------------
    // Save New Product
    // -----------------------------
    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute Product product, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("userId");
        if (sellerId == null) {
            return "redirect:/login";
        }

        productService.saveProduct(product, sellerId);
        return "redirect:/seller/dashboard";
    }

    // -----------------------------
    // Delete Product (Optional)
    // -----------------------------
    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam Long productId, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("userId");
        if (sellerId == null) {
            return "redirect:/login";
        }

        Product product = productService.getProductById(productId);
        if (product != null && product.getSellerId().equals(sellerId)) {
            productService.deleteProduct(productId);
        }

        return "redirect:/seller/dashboard";
    }
    
 // Show edit form
    @GetMapping("/edit-product")
    public String showEditProductForm(@RequestParam Long productId, Model model, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("userId");
        Product product = productService.getProductById(productId);

        // Only allow editing if this product belongs to the logged-in seller
        if (product != null && product.getSellerId().equals(sellerId)) {
            model.addAttribute("product", product);
            return "seller-edit-product"; // points to Thymeleaf template
        }

        return "redirect:/seller/dashboard";
    }
    
    // Save the edited product
    @PostMapping("/update-product")
    public String updateProduct(@ModelAttribute Product product, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("userId");

        Product existing = productService.getProductById(product.getId());
        if (existing != null && existing.getSellerId().equals(sellerId)) {
            productService.saveProduct(product, sellerId);
        }

        return "redirect:/seller/dashboard";
    }

}
