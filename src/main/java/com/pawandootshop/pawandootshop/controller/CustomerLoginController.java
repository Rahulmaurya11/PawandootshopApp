package com.pawandootshop.pawandootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import com.pawandootshop.pawandootshop.DTO.requestDTO;
import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.repository.customerRepository;
import com.pawandootshop.pawandootshop.service.JwtService;
import com.pawandootshop.pawandootshop.service.TokenLogService;
import com.pawandootshop.pawandootshop.service.customerService;

@Controller
public class CustomerLoginController {

	@Autowired
	private customerService service;

	@Autowired
	TokenLogService tokenservice;

	// @Autowired
//	JwtService jwt;

	@GetMapping("/login")
	public String loginpage(Model model) {

		model.addAttribute("request", new requestDTO());
		return "login";
	}

	// In this block of code we have giv
	/*
	 * 
	 * @PostMapping("/Customerlogin") public String login(@ModelAttribute requestDTO
	 * request, Model model)
	 * 
	 * {
	 * 
	 * responseDTO response = new responseDTO(); Customer customer =
	 * service.login(request); if (customer!= null) { String token =
	 * tokenservice.generateToken(customer.getId(), customer.getUserName());
	 * 
	 * response.setToken(token); response.setMessage("login sucessful"); } else
	 * response.setMessage("invalid username or password ");
	 * //response.setMessage(customer != null ? " login sucessful " :
	 * "Invalid username or password "); model.addAttribute("request", request);
	 * model.addAttribute("response", response);
	 * 
	 * return "login";
	 * 
	 * 
	 * }
	 * 
	 */

	@PostMapping("/Customerlogin")
	public String login(@ModelAttribute requestDTO request, Model model) {

		responseDTO response = new responseDTO();
		Customer customer = service.login(request);
		if (customer != null) {
			// generate token
			String token = tokenservice.generateToken(customer.getId(), customer.getUserName());
			// String token = jwt.generateToken(request);
			// response prepration
			response.setMessage("Hello" +customer.getFirstName());
			response.setToken(token);
			// response send

			// return "redirect:/getallproduct";

		}

		else {
			response.setMessage("invalid username or password ");
		}
		// response.setMessage(customer != null ? " login sucessful " : "Invalid
		// username or password ");
		model.addAttribute("request", request);
		model.addAttribute("response", response);

		return "login";

	}

}
