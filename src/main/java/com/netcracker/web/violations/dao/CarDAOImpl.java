package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarDAOImpl implements CarDAO {

    private static String url = "jdbc:postgresql://localhost:5432/violations";
    private static String username = "postgres";
    private static String password = "avoeva";
    private static Connection connection;

    static {
        System.out.println("Connecting...");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }


    @Override
    public void save(Car car) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Car (number, model, owner) VALUES('" + car.getNumber() +
                    "'," + car.getModel() + ",'" + car.getOwner() + "')";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(int id, Car car) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Car SET number =?, model=?, owner=? WHERE id=?");

            preparedStatement.setString(1, car.getNumber());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getOwner());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car get(int id) {
        Car car = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Car WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            car = new Car();

            car.setId(resultSet.getInt("id"));
            car.setNumber(resultSet.getString("number"));
            car.setModel(resultSet.getString("model"));
            car.setOwner(resultSet.getString("owner"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Car WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Car> allCars() {
        List<Car> cars = new ArrayList<>();
        try {
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
            e.printStackTrace();
        }
        return cars;
    }
}