package com.pawandootshop.pawandootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawandootshop.pawandootshop.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	
	
}
