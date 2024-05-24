package com.pawandootshop.pawandootshop.service;


import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pawandootshop.pawandootshop.DTO.requestDTO;
import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.repository.customerRepository;


@Service
public class customerService {
	
	@Autowired
	customerRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<Customer> getallCustomer(){
		 
	
		return repository.findAll();
	}
	

	public Customer addCustomer( Customer customerDetails )
	
	{			
		
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();	
		
			String hascode = bCryptPasswordEncoder.encode(customerDetails.getPassword());
			
			customerDetails.setPassword(hascode);

			return repository.save(customerDetails);
			
	}
		
	
	public Customer getcustomerbyid(Long id) {
		
		return repository.getReferenceById(id);
		
		
	}
	
	
	public Customer updateCustomer (Long id , Customer custdetails)
	{
    Optional<Customer> custDetailsDB = repository.findById(id);
    
    Customer customer = null;
    
    if (custDetailsDB.isPresent()) {
    	// storing optional object type to customer object type 
    	customer = custDetailsDB.get(); 
    	
    	
    	customer.setAddress(custdetails.getAddress());
    	customer.setPhoneNumber(custdetails.getPhoneNumber());
    	customer.setFirstName(custdetails.getFirstName());
    	customer.setPassword(custdetails.getPassword());
    	customer.setEmail(custdetails.getEmail());
    	customer.setLastName(custdetails.getEmail());
    	customer.setOrders(custdetails.getOrders());
    	repository.save(customer); 
    }  
    
    return customer; 
    
	}
	
	
  public ResponseEntity<?> deleteCustomer(Long id )
  
  {
		
		repository.deleteById(id);
		
		return ResponseEntity.ok("Deleted ");
		
  }
  
  
  public Customer login(requestDTO loginRequestDto) {
	  
      Optional<Customer> customerO = repository.findByuserName(loginRequestDto.getUserName());
      Customer customer1 = null;
              
      
      if(customerO.isPresent()) {
      	
    	  Customer customerdb = customerO.get();
      	
      	
//      	System.out.print("passwrod user: " + loginRequestDto.getPassword() + " from db:" + studentdb.getPassword());
        
      	if(passwordEncoder.matches(loginRequestDto.getPassword(), customerdb.getPassword())) 
      	
      	{
      		customer1 = customerdb;
      		
      	} 	
      	
      } 
     
      return customer1; 
  }

  
  
}
