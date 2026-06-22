package com.example.EcommerceApp.Controller;

import com.example.EcommerceApp.model.Role;
import com.example.EcommerceApp.model.User;
import com.example.EcommerceApp.service.ProductService;
import com.example.EcommerceApp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;   // ✅ CORRECT IMPORT
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/login";
        }
        return "admin-dashboard";
    }

    // USERS
    @GetMapping("/users")
    public String users(Model model, HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/login";
        }

        model.addAttribute("users", userService.getAllUsers());
        return "admin-users";
    }

    // SELLERS
    @GetMapping("/sellers")
    public String sellers(Model model, HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/login";
        }

        model.addAttribute("sellers", userService.getSellers());
        return "admin-sellers";
    }

    // PRODUCTS
    @GetMapping("/products")
    public String products(Model model, HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/login";
        }

        model.addAttribute("products", productService.getAllProducts());
        return "admin-products";
    }
    
 // DELETE PRODUCT (ADMIN ONLY)
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {

        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/login";
        }

        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/create-seller")
    public String createSellerForm(Model model, HttpSession session) {
        // Optional: check admin role
        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/login";
        }

        model.addAttribute("user", new User()); // empty User object for the form
        return "admin-create-seller"; // Thymeleaf template name
    }
    
    @PostMapping("/create-seller")
    public String saveSeller(@ModelAttribute("user") User user, HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN) {
            return "redirect:/login";
        }

        // Set role as SELLER
        user.setRole(Role.SELLER);
        // Save using your UserService
        userService.saveUser(user);

        return "redirect:/admin/sellers";
    }
    
 // DELETE USER
    @PostMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN)
            return "redirect:/login";

        userService.deleteSeller(id);
        return "redirect:/admin/users";
    }

    // DELETE SELLER + PRODUCTS
    @PostMapping("/delete-seller/{id}")
    public String deleteSeller(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN)
            return "redirect:/login";

        userService.deleteSeller(id);
        return "redirect:/admin/sellers";
    }

    // DELETE PRODUCT
    @PostMapping("/delete-product/{id}")
    public String deleteProduct1(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("role") != Role.ADMIN)
            return "redirect:/admin/products";

        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
