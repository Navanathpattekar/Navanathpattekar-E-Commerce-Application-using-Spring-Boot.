package com.example.EcommerceApp.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(HttpSession session, Model model) {
        Object username = session.getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username.toString());
        } else {
            model.addAttribute("username", null);
        }
        return "home"; 
    }
}
