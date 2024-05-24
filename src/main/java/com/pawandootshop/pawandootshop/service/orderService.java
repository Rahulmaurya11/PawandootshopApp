package com.pawandootshop.pawandootshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawandootshop.pawandootshop.model.Order;
import com.pawandootshop.pawandootshop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class orderService {

    @Autowired
    private OrderRepository orderRepository;

    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    
    public Order createOrder(Order order) {
        
    	return orderRepository.save(order);

    }

    
    public Order updateOrder(Long id, Order orderDetails) 
    {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setCustomer(orderDetails.getCustomer());
            order.setOrderDate(orderDetails.getOrderDate());
            order.setTotalAmount(orderDetails.getTotalAmount());
            return orderRepository.save(order);
        }
        return null;
    }
    
    public void deleteOrder(Long id) {
    
    orderRepository.deleteById(id);
   
    }
}
