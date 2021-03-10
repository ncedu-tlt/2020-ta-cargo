package com.netcracker.controller;


import com.netcracker.model.Order;
import com.netcracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public List<Order> showAll () {
        return orderService.displayAll();
    }

    @PostMapping("/order")
    public Order create (@RequestBody Order order){
        orderService.create(order);
        return  order;
    }

    @DeleteMapping ("/order/{id}")
    public ResponseEntity<?> delete (@PathVariable(name = "id") int id){
        boolean delete = orderService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping ("/order/showCity/{city}")
    public  List<Order> searchByCity (@PathVariable(name = "city") String city){
        return orderService.searchByCity(city);
    }

    @PatchMapping("/order")
    public ResponseEntity<?> modify(@RequestBody Order order){
        final boolean update = orderService.modify(order);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
