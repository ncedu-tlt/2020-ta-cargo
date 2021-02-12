package com.netcracker.controller;

import com.netcracker.model.Order;
import com.netcracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/id")
    public List<Order> showAll () {
        orderService.showAll();
        return orderService.showAll();
    }

    @PostMapping("/order/create")
    public Order create (@RequestBody Order order){
        orderService.createOrder(order);
        return  order;
    }

    @DeleteMapping ("/order/delete")
    public Order delete (@RequestBody Order order){
        orderService.deleteOrder(order);
        return  order;
    }
}
