package com.netcracker.controller;
import com.netcracker.model.Order;
import com.netcracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<Order> create(@RequestBody Order order){
        orderService.create(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping("/order/patch")
    public ResponseEntity<?> edit(@RequestBody Order update) {
        return ResponseEntity.ok("resource order updated");
    }


    @GetMapping("order")
    public ResponseEntity<List<Order>>readeAll(){
        final List<Order> orderList = orderService.readAll();
        return orderList != null && !orderList.isEmpty()
                ? new ResponseEntity<>(orderList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> readId(@PathVariable(name = "id") int id){
        final Order order = orderService.read(id);
        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id,
                                    @RequestBody Order order){
        final  boolean update = orderService.update(id, order);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = orderService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
