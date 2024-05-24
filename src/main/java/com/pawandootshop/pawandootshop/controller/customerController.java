package com.pawandootshop.pawandootshop.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.service.customerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customer")
public class customerController {

	@Autowired
	customerService service;

	@GetMapping
	public List<Customer> getallCustomer()
	{
		
		return service.getallCustomer();

	}
	
	@GetMapping("/{id}")
	
	public Customer getcustomerbyid(@PathVariable Long id ) 
	{
		return service.getcustomerbyid(id);
	}
	
	
	

	@PostMapping
	public Customer createCustomer(@RequestBody Customer custdetails)
	{
		
		return service.addCustomer(custdetails); 
		
	}
	
	
	@PutMapping("/{id}")
	public Customer updateCustomer (@PathVariable Long id , @RequestBody Customer custdetails)
	{
		return service.updateCustomer(id,custdetails);
		
	}
	

	
	
	@DeleteMapping
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id )
	{
		return service.deleteCustomer(id);
		
	}

}