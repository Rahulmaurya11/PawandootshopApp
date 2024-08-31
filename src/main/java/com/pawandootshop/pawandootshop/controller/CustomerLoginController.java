package com.pawandootshop.pawandootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pawandootshop.pawandootshop.DTO.JwtRequest;
import com.pawandootshop.pawandootshop.DTO.JwtResponse;
import com.pawandootshop.pawandootshop.DTO.requestDTO;
import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.security.JwtService;
import com.pawandootshop.pawandootshop.service.TokenLogService;
import com.pawandootshop.pawandootshop.service.customerService;

@Controller

public class CustomerLoginController {

	//@Autowired
	//private customerService service;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	// @Autowired
//	TokenLogService tokenservice;

	@Autowired
	JwtService helper;

	@GetMapping("/login")
	public String loginpage(Model model) {

		model.addAttribute("request", new JwtRequest());
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

	/*
	 * @PostMapping("/Customerlogin") public String login(@ModelAttribute requestDTO
	 * request, Model model) {
	 * 
	 * responseDTO response = new responseDTO(); Customer customer =
	 * service.login(request); if (customer != null) { // generate token
	 * 
	 * String token = tokenservice.generateToken(customer.getId(),
	 * customer.getUserName()); // String token = jwt.generateToken(request) //
	 * response prepration response.setMessage("Hello" + customer.getFirstName());
	 * response.setToken(token); // response send
	 * 
	 * // return "redirect:/getallproduct";
	 * 
	 * }
	 * 
	 * else {
	 * 
	 * response.setMessage("invalid username or password ");
	 * 
	 * } // response.setMessage(customer != null ? " login sucessful " : "Invalid //
	 * username or password "
	 * 
	 * model.addAttribute("request", request); model.addAttribute("response",
	 * response);
	 * 
	 * return "login";
	 * 
	 * }
	 */

	@PostMapping("/Customerlogin")
	public String login(@ModelAttribute JwtRequest request, Model model) {

		this.doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		JwtResponse response = new JwtResponse(token, "Hello " + userDetails.getUsername());

		model.addAttribute("request", request);
		model.addAttribute("response", response);

		return "login";

	}

	private void doAuthenticate(String email, String password) {

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
		try {
			manager.authenticate(authentication);

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}

	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {

		return "Credentials Invalid !!";

	}

}
