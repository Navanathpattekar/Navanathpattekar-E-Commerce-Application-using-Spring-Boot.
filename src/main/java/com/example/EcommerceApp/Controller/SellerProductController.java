package com.example.EcommerceApp.Controller;

import com.example.EcommerceApp.model.*;
import com.example.EcommerceApp.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/seller/products")
public class SellerProductController {

    private final ProductService productService;

    public SellerProductController(ProductService productService) {
        this.productService = productService;
    }

    // SELLER PRODUCT LIST
    @GetMapping
    public String sellerProducts(HttpSession session, Model model) {

        Long sellerId = (Long) session.getAttribute("userId");

        if (sellerId == null) {
            return "redirect:/login";
        }

        model.addAttribute("products",
                productService.getSellerProducts(sellerId));

        return "seller-products";
    }

    // ADD PRODUCT FORM
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", Category.values());
        return "add-product";
    }

    // SAVE PRODUCT
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             HttpSession session) {

        Long sellerId = (Long) session.getAttribute("userId");

        if (sellerId == null) {
            return "redirect:/login";
        }

        productService.saveProduct(product, sellerId);
        return "redirect:/seller/products";
    }
}
