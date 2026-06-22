package com.example.EcommerceApp.service;

import com.example.EcommerceApp.model.CartItem;
import com.example.EcommerceApp.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartRepo;

    public CartService(CartItemRepository cartRepo) {
        this.cartRepo = cartRepo;
    }

    public List<CartItem> getCartItems() {
        return cartRepo.findAll();
    }

    public void addItem(String title, String description, double price, String imageUrl) {
        CartItem item = new CartItem();
        item.setTitle(title);
        item.setDescription(description);
        item.setPrice(price);
        item.setQuantity(1);
        item.setImageUrl(imageUrl); // ✅ crucial
        cartRepo.save(item);
    }

    public void removeItem(Long id) {
        cartRepo.deleteById(id);
    }

    public void clearCart() {
        cartRepo.deleteAll();
    }

    public void updateQuantity(Long id, int quantity) {
        CartItem item = cartRepo.findById(id).orElse(null);
        if (item != null) {
            item.setQuantity(quantity);
            cartRepo.save(item);
        }
    }
    
  

}
