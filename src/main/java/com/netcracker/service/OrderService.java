package com.netcracker.service;

import com.netcracker.model.Order;
import com.netcracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public Order createOrder(Order order){
        orderRepository.save(order);
        return order;
    }

    public List<Order> showAll(){
        return orderRepository.findAll();
    }

    public Order deleteOrder (Order order){
        if (orderRepository.existsById(order.getId())){
            orderRepository.delete((order));
        }
        return order;
    }
}
