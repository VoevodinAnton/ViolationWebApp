package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    void save(Car car);

    void update(int id, Car car);

    Car get(int id);

    void delete(int id);

    List<Car> getAllCars();

    void importFromFile(List<Car> cars);

    void conductAudit(Car oldCar, Car newCar, String action) throws SQLException;
}
