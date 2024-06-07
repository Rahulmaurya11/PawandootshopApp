package com.pawandootshop.pawandootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.OrderItem;
import com.pawandootshop.pawandootshop.model.Product;
import com.pawandootshop.pawandootshop.repository.OrderItemRepository;

@Controller
@ControllerAdvice 
public class OrderControllerThym {

	@Autowired
	OrderItemRepository orderRepository;
	
   @PostMapping("/saveOrderItems")
	public String  Addorder (@ModelAttribute Product Product , Model model ) {
	     
	   
	   return "bucketOrderResponse";
   }
}
