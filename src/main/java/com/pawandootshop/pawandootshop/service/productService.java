package com.pawandootshop.pawandootshop.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pawandootshop.pawandootshop.model.Product;
import com.pawandootshop.pawandootshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    private ProductRepository productRepository;

    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Page<Product> getAllProductPageSort(int pageNo, int pageSize, String sortBy)
    { 
    	
    	Pageable pageable =  PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
       return productRepository.findAllByThis(pageable);
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
