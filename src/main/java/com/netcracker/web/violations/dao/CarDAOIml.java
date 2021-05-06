package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CarDAOIml implements CarDAO {

    @Override
    public int save(Car car) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Car car) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Car get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Integer id) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Car> allCars() {
        // TODO Auto-generated method stub
        return null;
    }
}
