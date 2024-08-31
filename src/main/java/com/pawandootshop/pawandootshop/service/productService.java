package com.pawandootshop.pawandootshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.pawandootshop.pawandootshop.DTO.Productresponsedto;
import com.pawandootshop.pawandootshop.DTO.responseDTO;
import com.pawandootshop.pawandootshop.model.Product;
import com.pawandootshop.pawandootshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class productService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<Product> getAllProductsThym() {
		List<Product> products = productRepository.findAll();
		return products;
			
	}
	
	
	public List<Productresponsedto> getAllProducts(){
		List<Product> products = productRepository.findAll();
	
		return products.stream().map(product -> modelMapper.map(product, Productresponsedto.class))
			.collect(Collectors.toList());
	
		
	}

	public Productresponsedto getProductById(Long id) {

		Optional<Product> product = productRepository.findById(id);
		return modelMapper.map(product, Productresponsedto.class);
	}
/*
	public ResponseEntity<Page<Product>> getAllProductPageSort(int pageNo, int pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> allproduct = productRepository.findAll(pageable);
		List<Product> listproduct = allproduct.getContent();

		//Productresponsedto response = new Productresponsedto();
		//response.setProduct(listproduct);
		//response.setPagenumber(allproduct.getNumber());
		//response.setPageSize(allproduct.getSize());

		//return ResponseEntity.ok(response);
	 return ResponseEntity.ok(productRepository.findAllByThis(pageable)) ;

	}
	 
*/

	
	public ResponseEntity<List<Productresponsedto>> getAllProductPageSort(int pageNo, int pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Product> allproduct = productRepository.findAll(pageable);
		List<Product> listproduct = allproduct.getContent();

		//Productresponsedto response = new Productresponsedto();
		//response.setProduct(listproduct);
		//response.setPagenumber(allproduct.getNumber());
		//response.setPageSize(allproduct.getSize());

		//return ResponseEntity.ok(response);
		
		
	 return ResponseEntity.ok(listproduct.stream().map(product->modelMapper.map(product, Productresponsedto.class)).collect(Collectors.toList())) ;
	 
	}
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	
	
	
	public Product updateProduct(Long id, Product productDetails) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setName(productDetails.getName());
			product.setDescription(productDetails.getDescription());
			product.setPrice(productDetails.getPrice());
			product.setQuantityAvailable(productDetails.getQuantityAvailable());
			return productRepository.save(product);
		}
		return null;
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
