package com.netcracker.service;

import com.netcracker.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService {

    private static final Map<Integer, Order> ORDER_MAP = new HashMap<>();
    private static final AtomicInteger ORDER_ID_HOLDER = new AtomicInteger();



    public void create(Order order) {
        final int id = ORDER_ID_HOLDER.incrementAndGet();
        order.setOrderId(id);
        ORDER_MAP.put(id, order);
    }


    public List<Order> readAll() {
        List<Order> orderList = new ArrayList<>(ORDER_MAP.values());
        return orderList;
    }


    public Order read(int id) {
        Order order = ORDER_MAP.get(id);
        return order;
    }


    public boolean update(Order object) {
        if (ORDER_MAP.containsKey(object.getOrderId())) {
            ORDER_MAP.put(object.getOrderId(), object);
            return true;
        } else return false;
    }



    public boolean delete(int id) {
        if (ORDER_MAP.containsKey(id)) {
            ORDER_MAP.remove(id);
            return true;
        } else return false;
    }


    public boolean updatePartial(Order order) {
        if (ORDER_MAP.containsKey(order.getOrderId())) {
            Order orderModify = read(order.getOrderId());
            orderModify.setBoxId(order.getBoxId());
            orderModify.setDestinationID(order.getDestinationID());
            orderModify.setDriverId(order.getDriverId());
            orderModify.setName(order.getName());
            orderModify.setOrderId(order.getOrderId());
            orderModify.setPrice(order.getPrice());
            orderModify.setStatus(order.getStatus());
            orderModify.setReceiverId(order.getReceiverId());
        return true;
        } else return false;
    }
}
