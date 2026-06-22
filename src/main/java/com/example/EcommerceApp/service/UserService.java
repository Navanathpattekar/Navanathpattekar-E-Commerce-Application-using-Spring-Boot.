package com.example.EcommerceApp.service;

import com.example.EcommerceApp.model.Role;
import com.example.EcommerceApp.model.User;
import com.example.EcommerceApp.repository.ProductRepository;
import com.example.EcommerceApp.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    
    @Autowired
    private ProductRepository productRepository;

    public User validateUser(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public void saveUser(User user) {
        repo.save(user);
    }

    public User getUserById(Long id) {
        return repo.findById(id).orElse(null);
    }

	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public List<User> getAllUsers() {
        return repo.findAll();
    }

    public List<User> getSellers() {
        return repo.findByRole(Role.SELLER);
    }

    public List<User> getAdmins() {
        return repo.findByRole(Role.ADMIN);
    }
    
    @Transactional
    public void deleteSeller(Long sellerId) {
        productRepository.deleteBySellerId(sellerId);
        repo.deleteById(sellerId);
    }

}
