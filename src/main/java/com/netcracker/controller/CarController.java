package com.netcracker.controller;


import com.netcracker.model.Car;
import com.netcracker.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/car")
    public ResponseEntity<?> create(@RequestBody Car car){
        carService.create(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/car")
    public ResponseEntity<List<Car>>readAll(){
        final List<Car> carList = carService.readAll();
        return carList != null && !carList.isEmpty()
                ? new ResponseEntity<>(carList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> readId(@PathVariable(name = "id") int id){
        final Car car = carService.readById(id);
        return car !=null
                ? new ResponseEntity<>(car, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/car")
    public ResponseEntity<?> update(@RequestBody Car car){
        final  boolean update = carService.update(car);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = carService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/car")
    public ResponseEntity<?> updatePartial(@RequestBody Car car ){
        final boolean updateField = carService.updatePartial(car);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
