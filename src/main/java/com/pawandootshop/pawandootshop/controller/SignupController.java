package com.pawandootshop.pawandootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.repository.customerRepository;

@Controller

public class SignupController {
	
	@Autowired
	customerRepository repository; 
	
	@Autowired
	private PasswordEncoder passwordencoder;

	@GetMapping("/home")
	public String homepage () {
		return "homepage";
	}

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("customer", new Customer());
        
        return "signup";
    
    }

    @PostMapping("/SignUpSubmit")
    
    public String submitSignUpForm(@ModelAttribute Customer customer, Model model) 
    
    {
    	
    	// Process the customer data (save to the database)
    	
    	
    	customer.setPassword(passwordencoder.encode(customer.getPassword()));    
    	repository.save(customer);
        model.addAttribute("customer", customer); 
        
        return "signupSuccess"; 
   
    }  
    
}

