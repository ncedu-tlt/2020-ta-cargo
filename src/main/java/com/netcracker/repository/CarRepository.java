package com.netcracker.repository;

import com.netcracker.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository  extends JpaRepository<Car, Integer> {
}
