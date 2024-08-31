package com.pawandootshop.pawandootshop.controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pawandootshop.pawandootshop.DTO.JwtRequest;
import com.pawandootshop.pawandootshop.DTO.JwtResponse;
import com.pawandootshop.pawandootshop.DTO.customerDTO;
import com.pawandootshop.pawandootshop.DTO.requestDTO;
import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.model.TokenLog;
import com.pawandootshop.pawandootshop.repository.TokenLogRepository;
import com.pawandootshop.pawandootshop.security.JwtService;
import com.pawandootshop.pawandootshop.service.TokenLogService;
import com.pawandootshop.pawandootshop.service.customerService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	customerService custservice;

	@Autowired
	TokenLogService tokenlogservice;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	TokenLogRepository repository;

	@Autowired
	JwtService helper;

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/customerLogin")
	public responseDTO customerLogin(@RequestBody requestDTO loginRequestDto) {

		System.out.println("customer login block ");
		responseDTO loginResponseDto = new responseDTO();
		// Logic flow start

		Customer customer = custservice.login(loginRequestDto);

		// if not found send error
		if (customer == null) {

			loginResponseDto.setMessage("User credentials are not correct");
			return loginResponseDto;

		}

		// String jwttoken = jwtservice.generateToken(customer);
		// Long expiration = jwtservice.getExpirationTime();

		String token = tokenlogservice.generateToken(customer.getId(), customer.getUserName());

		// response preparation

		loginResponseDto.setStatus(true);
		loginResponseDto.setMessage("Login Successfully");
		loginResponseDto.setToken(token);

		// loginResponseDto.setExpiresin(expiration);

		// Response preparation end
		// Response send
		return loginResponseDto;

	}

	/*
	 * public ResponseEntity<responseDTO> authenticate(@RequestBody requestDTO
	 * loginUserDto) { Customer authenticatedUser =
	 * custservice.authenticate(loginUserDto);
	 * 
	 * String jwtToken = jwtservice.generateToken(authenticatedUser);
	 * 
	 * // LoginResponse loginResponse = new
	 * LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime(
	 * )); responseDTO response = new responseDTO();
	 * response.setExpiresin(jwtservice.getExpirationTime());
	 * response.setToken(jwtToken); return ResponseEntity.ok(response);
	 * 
	 * }
	 */

	@PostMapping("/verifytoken")
	public boolean validateToken(@RequestHeader("Authorization") String token)

	{

		// if method returns true then token is valid , request is authentic .
		TokenLog dbtoken = repository.findBytoken(token);
		LocalDateTime currenttime = LocalDateTime.now();
		if (currenttime.isBefore(dbtoken.getExpiryTime())) {

			return true;

		}

		return false;

	}

	@GetMapping("converttohash")
	public String convertToHash(@RequestParam String clearText) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String cipherText = passwordEncoder.encode(clearText);

		return cipherText;

	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		this.doAuthenticate(request.getEmail(), request.getPassword());

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		String token = this.helper.generateToken(userDetails);

		JwtResponse response = new JwtResponse(token, userDetails.getUsername());
		return new ResponseEntity<>(response, HttpStatus.OK);
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
