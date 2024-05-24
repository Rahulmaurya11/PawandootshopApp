package com.pawandootshop.pawandootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pawandootshop.pawandootshop.DTO.requestDTO;
import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.repository.customerRepository;
import com.pawandootshop.pawandootshop.service.customerService;

@Controller
public class CustomerLogin {

	@Autowired
	private customerService service;

	
	@GetMapping("/login")
	public String loginpage(Model model) {

		model.addAttribute("request", new requestDTO());
		return "login";
	}

	
	@PostMapping("/Customerlogin")
	public String login(@ModelAttribute requestDTO request, Model model)

	{

		responseDTO response = new responseDTO();
		Customer customer = service.login(request);
		if (customer == null) {

			response.setMessage("Invalid username or password ");
		}

		else {

			response.setMessage(" login sucessful ");
		
		}
		
		model.addAttribute("request", request);
		model.addAttribute("response", response);

		return "login";

	}

}
