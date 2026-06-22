package com.example.EcommerceApp.repository;

import com.example.EcommerceApp.model.Role;
import com.example.EcommerceApp.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    
    boolean existsByEmail(String email);

	User findByEmailAndPassword(String email, String password);
	
	List<User> findByRole(Role role);

}

