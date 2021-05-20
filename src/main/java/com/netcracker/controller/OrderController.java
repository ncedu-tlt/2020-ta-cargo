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
    public List<Order> showAll() {
        return orderService.displayAll();
    }

    @GetMapping("/order/boxClientIdAndStatus/{id}/{status}")
    public List<Order> showAllByBoxClientIdAndStatus(@PathVariable Integer id, @PathVariable String status) {
        return orderService.displayAllByBoxClientIdAndStatus(id, status);
    }

    @GetMapping("/order/boxClientIdAndNotStatus/{id}/{status}")
    public List<Order> showAllByBoxClientIdAndNotStatus(@PathVariable Integer id, @PathVariable(name = "status") String status) {
        return orderService.displayAllByBoxClientIdAndNotStatus(id, status);
    }

    @GetMapping("/order/driverIdAndStatus/{id}/{status}")
    public List<Order> showAllByDriverIdAndStatus(@PathVariable Integer id, @PathVariable(name = "status") String status) {
        return orderService.displayAllByDriverIdAndStatus(id, status);
    }

    @GetMapping("/order/byLocDestTypePrice/{locCity}/{destCity}/{typeId}/{price}/{status}")
    public List<Order> showByLocationAndDestinationAndTypeAndPrice(@PathVariable(name = "locCity") String locCity, @PathVariable(name = "destCity") String destCity, @PathVariable(name = "typeId") Integer typeId, @PathVariable(name = "price") Integer price, @PathVariable(name = "status") String status) {
        return orderService.displayByLocationAndDestinationAndTypeAndPrice(locCity, destCity, typeId, price, status);
    }

    @GetMapping("/order/boxClientId/{id}")
    public List<Order> showAllByBoxClientId(@PathVariable Integer id) {
        return orderService.displayAllByBoxClientId(id);
    }

    @GetMapping("/order/status/{name}")
    public List<Order> showAllByStatusName(@PathVariable String name) {
        return orderService.displayAllByStatusName(name);
    }

    @PostMapping("/order")
    public Order create(@RequestBody Order order) {
        orderService.create(order);
        return order;
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        boolean delete = orderService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/order/showCity/{city}")
    public List<Order> searchByCity(@PathVariable(name = "city") String city) {
        return orderService.searchByCity(city);
    }

    @PatchMapping("/order")
    public ResponseEntity<?> modify(@RequestBody Order order) {
        final boolean update = orderService.modify(order);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }



    @GetMapping("/order/displayById/{id}")
    public Order displayById(@PathVariable(name = "id") Integer id) {
        return orderService.displayById(id);
    }
}
