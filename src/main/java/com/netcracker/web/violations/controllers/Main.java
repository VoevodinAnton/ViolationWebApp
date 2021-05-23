package com.netcracker.web.violations.controllers;

import com.netcracker.web.violations.model.Car;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        /*
        String url = "jdbc:postgresql://localhost:5432/violations";
        String username = "postgres";
        String password = "avoeva";
        System.out.println("Connecting...");
        List<Car> cars = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            System.out.println("Connection successful!");
            Statement statement = connection.createStatement();
            Car car = new Car();
            car.setNumber("В123ПЕ163");
            car.setOwner("Петров Петр Петрович");
            car.setModel("Toyota Camry");
            String SQL = "INSERT INTO Car (number, model, owner) VALUES('" + car.getNumber() +
                    "','" + car.getModel() + "','" + car.getOwner() + "')";
            //ResultSet resultSet = statement.executeQuery(SQL);


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


         */

        String text = "asddf a123DF163 asf";
        Pattern pattern = Pattern.compile("^[а-яё\\-\\s][0-9]{3}(?<!0{3})[а-яё\\-\\s]{2}[0-9]{3}$");

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start=matcher.start();
            int end=matcher.end();
            System.out.println("Найдено совпадение " + text.substring(start,end) + " с "+ start + " по " + (end-1) + " позицию");
        }


    }
}
