package com.example.EcommerceApp.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.EcommerceApp.service.ProductService;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @GetMapping("/men")
    public String menPage(org.springframework.ui.Model model) {
        model.addAttribute(
            "products",
            productService.getProductsByCategory("MEN")
        );
        return "men";
    }
    
    @GetMapping("/women")
    public String womenPage(Model model) {
        model.addAttribute("products",
                productService.getProductsByCategory("WOMEN"));
        return "women";
    }
    
    @GetMapping("/kids")
    public String kidsPage(Model model) {
        model.addAttribute("products",
            productService.getProductsByCategory("KIDS"));
        return "kids";
    }
    
    @GetMapping("/shoes")
    public String shoesPage(Model model) {
        model.addAttribute("products",
            productService.getProductsByCategory("SHOES"));
        return "shoes";
    }

    @GetMapping("/jewels")
    public String jewelsPage(Model model) {
        // Fetch products in JEWELLERY category from your service
        model.addAttribute("jewels", productService.getProductsByCategory("JEWELRY"));
        // Return the template name (without .html)
        return "jewels";
    }

    @GetMapping("/beauty")
    public String beautyPage(Model model) {
        // Fetch beauty products from the service by category
        model.addAttribute("products", productService.getProductsByCategory("BEAUTY"));
        return "beauty"; // corresponds to beauty.html in templates folder
    }

    
    @GetMapping("/decoration")
    public String decorationPage(Model model) {
        // Fetch products from the service by category "DECORATION"
        model.addAttribute("products", productService.getProductsByCategory("DECORATION"));
        return "decoration"; // corresponds to decoration.html in templates folder
    }

    @GetMapping("/furniture")
    public String furniturePage(Model model) {
        // Fetch products from the service by category "FURNITURE"
        model.addAttribute("products", productService.getProductsByCategory("FURNITURE"));
        return "furniture"; // corresponds to furniture.html in templates folder
    }



}
