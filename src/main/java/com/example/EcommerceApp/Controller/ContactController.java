package com.example.EcommerceApp.Controller;

import com.example.EcommerceApp.model.ContactMessage;
import com.example.EcommerceApp.service.ContactMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    private final ContactMessageService contactService;

    public ContactController(ContactMessageService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contactUs")
    public String contactPage(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contactUs";
    }

    @PostMapping("/contactUs")
    public String submitContact(@ModelAttribute("contactMessage") ContactMessage contactMessage,
                                Model model) {
        contactService.saveMessage(contactMessage.getName(), contactMessage.getEmail(), contactMessage.getMessage());
        model.addAttribute("success", "Your message has been sent successfully!");
        model.addAttribute("contactMessage", new ContactMessage()); // reset form
        return "contactUs";
    }
}
