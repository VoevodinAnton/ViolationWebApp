package com.netcracker.web.violations.controllers;

import com.netcracker.web.violations.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/violations";
        String username = "postgres";
        String password = "avoeva";
        System.out.println("Connecting...");
        List<Car> cars = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            System.out.println("Connection successful!");
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Car";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Car car = new Car();

                car.setId(resultSet.getInt("id"));
                car.setNumber(resultSet.getString("number"));
                car.setModel(resultSet.getString("model"));
                car.setOwner(resultSet.getString("owner"));

                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        for (Car car: cars){
            System.out.println(car.getModel());
        }

    }
}
