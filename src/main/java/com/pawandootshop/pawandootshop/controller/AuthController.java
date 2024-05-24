package com.pawandootshop.pawandootshop.controller;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pawandootshop.pawandootshop.DTO.customerDTO;
import com.pawandootshop.pawandootshop.DTO.requestDTO;
import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.model.TokenLog;
import com.pawandootshop.pawandootshop.repository.TokenLogRepository;
import com.pawandootshop.pawandootshop.service.TokenLogService;
import com.pawandootshop.pawandootshop.service.customerService;


@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	customerService custservice;
	
	@Autowired
	TokenLogService tokenlogservice;
	
	@Autowired
	TokenLogRepository repository ; 
	
	
	@PostMapping("/customerLogin")
	public responseDTO customerLogin(@RequestBody requestDTO loginRequestDto) {

	
		responseDTO  loginResponseDto = new responseDTO();

		// Logic flow start
		
		Customer customer = custservice.login(loginRequestDto);
	
		// if not found send error
		if (customer == null) {
			
			loginResponseDto.setMessage("User credentials are not correct");
			return loginResponseDto;     
			
		 
		} 
		
      String token = tokenlogservice.generateToken(customer.getId(), customer.getUserName());

          //response preparation 
           customerDTO customerdto = new customerDTO();
          
            customerdto.setUserName(customer.getUserName());
            customerdto.setLastName(customer.getLastName());
            customerdto.setUserName(customer.getUserName());
            
		loginResponseDto.setStatus(true);
		loginResponseDto.setMessage("Login Successfully");
		loginResponseDto.setToken(token);
		
		// Response preparation end

		// Response send
		return loginResponseDto;

	}
	
		
	@PostMapping("/verifytoken")
	public boolean   verifytoken (@RequestHeader("Authorization") String  token)
	
	{
		
		// if method returns true then token is valid , request is authentic . 
	        TokenLog dbtoken = repository.findBytoken(token);
	        LocalDateTime currenttime = LocalDateTime.now();
	        if (currenttime.isBefore(dbtoken.getExpiryTime())) {	
	        	
	        	return true ;
	        	
	        }	     
	        
	   return false ;
	
	}
	
}
