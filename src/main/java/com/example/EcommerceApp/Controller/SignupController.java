package com.example.EcommerceApp.Controller;

import com.example.EcommerceApp.model.Role;
import com.example.EcommerceApp.model.User;
import com.example.EcommerceApp.service.UserService;
import com.example.EcommerceApp.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model, HttpSession session) {

        // 🔴 Check duplicate email
        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already registered!");
            model.addAttribute("user", user);
            return "signup";
        }

        user.setRole(Role.USER); // Default role
        userService.saveUser(user);

        // Optional: redirect to login
        return "redirect:/login";
    }
}
