package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CarDAO {
    void save(Car car);

    void update(int id, Car car);

    Car get(int id);

    void delete(int id);

    List<Car> allCars();
}
