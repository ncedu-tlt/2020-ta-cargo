package com.netcracker.service;

import com.netcracker.exception.SomethingNotFoundException;
import com.netcracker.model.Order;
import com.netcracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        try{
        return orderRepository.findAll();
    }catch (Exception ex){
        throw new SomethingNotFoundException("There aren't any Orders");
    }
    }

    @Override
    public boolean delete(Integer id) {
        if (orderRepository.existsById(id)){
            orderRepository.deleteById((id));
            return true;
        } else return false;
    }
    Order displayById(Integer id){
       return orderRepository.findById(id).
               orElseThrow(() -> new SomethingNotFoundException("your Id " + id + " not found"));
    }

    public boolean isItHere (Integer id){
        if(orderRepository.existsById(id)){
            return true;
        }else return false;
    }
    public List<Order> searchByCity (String city){
       return orderRepository.findOrderByLocation_City(city);
    }


 }
