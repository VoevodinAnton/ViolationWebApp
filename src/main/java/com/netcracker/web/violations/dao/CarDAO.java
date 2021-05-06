package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;

import java.util.List;

public interface CarDAO {
    int save(Car car);
    int update(Car car);
    Car get(int id);
    int delete(Integer id);
    List<Car> allCars();
}
