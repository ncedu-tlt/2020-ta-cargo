package com.netcracker.service;

import com.netcracker.model.Car;
import com.netcracker.model.Order;
import com.netcracker.repository.CarRepository;
import com.netcracker.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CarService implements Serviceable<Car> {

    private final CarRepository carRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CarService(CarRepository carRepository,
                      OrderRepository orderRepository) {
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Car create(Car object) {
        carRepository.saveAndFlush(object);
        return object;
    }

    @Override
    public List<Car> displayAll() {
        return carRepository.findAll();
    }

    public Car displayById(Integer id) {
        return carRepository.findById(id).get();
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
        Order order = orderRepository.findById(id).get();
        Car car = order.getDriver().getCar();
        return car;
    }
}
