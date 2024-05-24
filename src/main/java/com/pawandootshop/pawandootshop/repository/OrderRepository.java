package com.pawandootshop.pawandootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pawandootshop.pawandootshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	

}
