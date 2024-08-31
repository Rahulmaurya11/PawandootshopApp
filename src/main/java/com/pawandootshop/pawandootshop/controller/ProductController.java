package com.pawandootshop.pawandootshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pawandootshop.pawandootshop.DTO.Productresponsedto;
import com.pawandootshop.pawandootshop.model.Product;
import com.pawandootshop.pawandootshop.service.productService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	productService productService;

	@GetMapping("/paginate")
	public ResponseEntity<List<Productresponsedto>> getAllProducts(

			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy

	)

	{

		return productService.getAllProductPageSort(pageNo, pageSize, sortBy);
	}

	@GetMapping
	public List<Productresponsedto> getAllProducts() {

		System.out.println("get block 1");

		return productService.getAllProducts();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Productresponsedto> getProductById(@PathVariable Long id) {
		Productresponsedto product = productService.getProductById(id);
		if (product != null) {
			return ResponseEntity.ok(product);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {

		Product createdProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		Product updatedProduct = productService.updateProduct(id, productDetails);
		if (updatedProduct != null) {
			return ResponseEntity.ok(updatedProduct);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok("deleted");
	}

}