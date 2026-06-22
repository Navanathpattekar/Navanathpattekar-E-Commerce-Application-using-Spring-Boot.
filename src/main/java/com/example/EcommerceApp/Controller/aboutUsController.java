package com.example.EcommerceApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class aboutUsController {
	 	@GetMapping("/home1")
	    public String homePage() {
	        return "home"; 
	    }

	    @GetMapping("/aboutUs")
	    public String aboutUsPage() {
	        return "aboutUs"; 
	    }

	    @GetMapping("/contactUs1")
	    public String contactUsPage() {
	        return "contactUs"; 
	    }
}
