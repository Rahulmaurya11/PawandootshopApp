package com.pawandootshop.pawandootshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.Customer;
import com.pawandootshop.pawandootshop.model.Product;
import com.pawandootshop.pawandootshop.service.productService;

@Controller
public class ProductControllerThym {

	@Autowired
	private productService productservice;

	@GetMapping("/getallproduct")
	public String getallproduct(Model model) {

		List<Product> products = productservice.getAllProducts();

		model.addAttribute("products", products);
	    

		return "productList";

	}

}
