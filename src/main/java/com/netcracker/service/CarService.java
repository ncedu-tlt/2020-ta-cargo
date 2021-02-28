package com.netcracker.service;

import com.netcracker.exception.SomethingNotFoundException;
import com.netcracker.model.Car;
import com.netcracker.model.Client;
import com.netcracker.model.Order;
import com.netcracker.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CarService implements Serviceable<Car> {

    private final CarRepository carRepository;
    private final OrderService orderService;

    @Autowired
    public CarService(CarRepository carRepository,
                      OrderService orderService) {
        this.carRepository = carRepository;
        this.orderService = orderService;
    }

    @Override
    public Car create(Car object) {
        carRepository.saveAndFlush(object);
        return object;
    }

    @Override
    public List<Car> displayAll() {
        try{
        return carRepository.findAll();
    }catch (Exception ex){
        throw new SomethingNotFoundException("There aren't any Cars");
    }
    }

    public Car displayById(Integer id) {
        return carRepository.findById(id).
                orElseThrow(() -> new SomethingNotFoundException("your Id " + id + " not found"));
    }

    @Override
    public boolean delete(Integer id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }else return false;
    }

    public boolean modify(Car ob) {
        if (carRepository.existsById(ob.getId())) {
            Car carForModify = displayById(ob.getId());
            carForModify.setName(ob.getName());
            carForModify.setVolume(ob.getVolume());
            carForModify.setLiftingCapacity(ob.getLiftingCapacity());
            carForModify.setClient(ob.getClient());
            carRepository.saveAndFlush(carForModify);
            return true;
        }else return false;
    }

    public Car displayCarByOrderId(Integer id){
        Order order = orderService.displayById(id);
        Client driver = order.getDriver();
        Car car = driver.getCar();
        return car;
    }
}
