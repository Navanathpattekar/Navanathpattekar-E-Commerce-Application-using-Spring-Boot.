package com.example.EcommerceApp.Controller;

import com.example.EcommerceApp.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // View Cart
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "cart";
    }

    // ADD TO CART (from Men page)
    @PostMapping("/add")
    public String addToCart(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam String imageUrl
    ) {
        cartService.addItem(title, description, price, imageUrl);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long id) {
        cartService.removeItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long id,
                             @RequestParam int quantity) {
        cartService.updateQuantity(id, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
