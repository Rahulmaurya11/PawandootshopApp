package com.pawandootshop.pawandootshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawandootshop.pawandootshop.model.OrderItem;

public interface OrderItemRepository extends JpaRepository <OrderItem, Long>{

}
