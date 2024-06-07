package com.pawandootshop.pawandootshop.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawandootshop.pawandootshop.model.Customer;

@Repository
public interface customerRepository extends JpaRepository<Customer,Long> {

	Optional<Customer> findByuserName(String userName);
	
}
