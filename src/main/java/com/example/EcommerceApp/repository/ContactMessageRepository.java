package com.example.EcommerceApp.repository;

import com.example.EcommerceApp.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
    // You can add custom queries here if needed
}
