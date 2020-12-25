package com.netcracker.service;

import com.netcracker.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService implements Serviceable<Order>{

    private static final Map<Integer, Order> ORDER_MAP = new HashMap<>();
    private static final AtomicInteger ORDER_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Order order) {
        final int id = ORDER_ID_HOLDER.incrementAndGet();
        order.setOrderId(id);
        ORDER_MAP.put(id,order);
    }

    @Override
    public List<Order> readAll() {
        List<Order> orderList = new ArrayList<>(ORDER_MAP.values());
        return orderList;
    }

    @Override
    public Order read(int id) {
        Order order = ORDER_MAP.get(id);
        return order;
    }

    @Override
    public boolean update(int id, Order object) {
        if (ORDER_MAP.containsKey(id)) {
            object.setOrderId(id);
            ORDER_MAP.put(id, object);
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        if (ORDER_MAP.containsKey(id)) {
            ORDER_MAP.remove(id);
            return true;
        }else return false;
    }

    public void edit(Order order){
        if  (ORDER_MAP.containsKey(order.getOrderId())) {
            Order orderModify = read(order.getOrderId());
            orderModify.setBoxId(order.getBoxId());
            orderModify.setDestinationID(order.getDestinationID());
            orderModify.setDriverId(order.getDriverId());
            orderModify.setName(order.getName());
            orderModify.setOrderId(order.getOrderId());
            orderModify.setPrice(order.getPrice());
            orderModify.setStatus(order.getStatus());
            orderModify.setReceiverId(order.getReceiverId());
        }
    }
}
