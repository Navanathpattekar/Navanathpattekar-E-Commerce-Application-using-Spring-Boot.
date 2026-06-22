package com.example.EcommerceApp.service;

import com.example.EcommerceApp.model.ContactMessage;
import com.example.EcommerceApp.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ContactMessageService {

    private final ContactMessageRepository repository;

    public ContactMessageService(ContactMessageRepository repository) {
        this.repository = repository;
    }

    public void saveMessage(String name, String email, String message) {
        ContactMessage contactMessage = new ContactMessage(
            name, email, message, LocalDateTime.now()
        );
        repository.save(contactMessage);
    }
}
