package com.netcracker.controller;

import com.netcracker.model.Car;
import com.netcracker.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/car")
    public Car create(@RequestBody Car car){
        carService.create(car);
        return car;
    }

    @GetMapping("/car")
    public ResponseEntity<List<Car>> displayAll(){
        final List<Car> carList = carService.displayAll();
        return carList != null && !carList.isEmpty()
                ? new ResponseEntity<>(carList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = carService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/car")
    public ResponseEntity<?> modify(@RequestBody Car car ){
        final boolean updateField = carService.modify(car);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/car/byOrderId/{id}")
    public ResponseEntity<Car> displayCarByOrderId(@PathVariable(name = "id") int id){
        final Car car = carService.displayCarByOrderId(id);
        return car != null
                ? new ResponseEntity<>(car, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
