package com.netcracker.service;

import com.netcracker.model.Order;
import com.netcracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements Serviceable<Order> {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> displayAll() {
        return orderRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        if (orderRepository.existsById(id)){
            orderRepository.deleteById((id));
            return true;
        } else return false;
    }
 }
