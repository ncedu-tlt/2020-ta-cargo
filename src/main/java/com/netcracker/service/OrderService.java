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

    public boolean modify(Order order){
        if(orderRepository.existsById(order.getId())){
            Order orderForModify = displayById(order.getId());
            orderForModify.setId(order.getId());
            orderForModify.setName(order.getName());
            orderForModify.setDestination(order.getDestination());
            orderForModify.setLocation(order.getLocation());
            orderForModify.setDriver(order.getDriver());
            orderForModify.setBox(order.getBox());
            orderForModify.setReceiver(order.getReceiver());
            orderForModify.setStatus(order.getStatus());
            orderForModify.setPrice(order.getPrice());
            orderRepository.saveAndFlush(orderForModify);
            return true;
        }else return false;
    }


    public List<Order> displayByReceiver(Integer id) {
        try{
            return orderRepository.findOrderByReceiverUserId(id);
        }catch (Exception ex){
            throw new SomethingNotFoundException("There aren't any Orders");
        }
    }

 }
