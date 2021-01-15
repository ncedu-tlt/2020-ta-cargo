package com.netcracker.service;

import com.netcracker.model.Car;
import com.netcracker.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements Serviceable<Car> {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void create(Car object) {
        carRepository.saveAndFlush(object);
    }

    @Override
    public List<Car> readAll() {
        return carRepository.findAll();
    }

    @Override
    public Car readById(Integer id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public boolean update(Car object) {
        if (carRepository.existsById(object.getId())) {
            carRepository.saveAndFlush(object);
            return true;
        }else return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }else return false;
    }

    @Override
    public boolean updatePartial(Car ob) {
        if (carRepository.existsById(ob.getId())) {
            Car carForModify = readById(ob.getId());
            carForModify.setName(ob.getName());
            carForModify.setVolume(ob.getVolume());
            carForModify.setLiftingCapacity(ob.getLiftingCapacity());
            carForModify.setUser(ob.getUser());
            carRepository.saveAndFlush(carForModify);
            return true;
        }else return false;
    }
}
