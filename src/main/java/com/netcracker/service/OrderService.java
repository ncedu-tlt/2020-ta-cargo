package com.netcracker.service;

import com.netcracker.model.Order;
import com.netcracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements Serviceable<Order> {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void create(Order order) {
        orderRepository.save(order);
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
