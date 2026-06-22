package com.example.EcommerceApp.Controller;

import com.example.EcommerceApp.model.Role;
import com.example.EcommerceApp.model.User;
import com.example.EcommerceApp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(String email, String password, HttpSession session, Model model) {
        User user = userService.validateUser(email, password);

        if (user == null) {
            model.addAttribute("error", "Invalid email or password!");
            model.addAttribute("user", new User());
            return "login";
        }

        if (user != null) {
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            if (user.getRole() == Role.ADMIN) return "redirect:/admin/dashboard";
            else if (user.getRole() == Role.SELLER) return "redirect:/seller/dashboard";
            else return "redirect:/home";
        }

        model.addAttribute("error", "Invalid email or password!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
