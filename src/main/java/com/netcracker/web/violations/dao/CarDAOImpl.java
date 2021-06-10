package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.CarAudit;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Component
public class CarDAOImpl implements CarDAO {

    private static String url = "jdbc:postgresql://localhost:5432/Violations";
    private static String username = "postgres";
    private static String password = "Vegetable*1";
    private static Connection connection;

    {
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
            PreparedStatement preparedStatement =  connection.prepareStatement("INSERT INTO Car (number, model, owner) VALUES(?,?,?)");

            preparedStatement.setString(1, car.getNumber());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getOwner());

            preparedStatement.executeUpdate();

            preparedStatement =  connection.prepareStatement("SELECT * FROM Car WHERE number=? AND model=? AND owner=?");

            preparedStatement.setString(1, car.getNumber());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getOwner());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Car car1 = createCar(resultSet);

            conductAudit(new Car(), car1, "Добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUnique(String number){
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM Car WHERE number=?");
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next())
                result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    @Override
    public void update(int id, Car car) {
        try {
            Car oldCar = get(id);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Car SET number =?, model=?, owner=? WHERE id=?");

            preparedStatement.setString(1, car.getNumber());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getOwner());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
            conductAudit(oldCar, car, "Изменен");

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
            car = createCar(resultSet);
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
            Car car = get(id);
            preparedStatement = connection.prepareStatement("DELETE FROM Car WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            //conductAudit(car, new Car(), "Удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Car";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Car car = createCar(resultSet);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }


    Car createCar(ResultSet resultSet){
        Car car = null;
        try {
            car = new Car();
            car.setId(resultSet.getInt("id"));
            car.setNumber(resultSet.getString("number"));
            car.setModel(resultSet.getString("model"));
            car.setOwner(resultSet.getString("owner"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return car;
    }

    @Override
    public void importFromFile(List<Car> cars){
        PreparedStatement preparedStatement = null;
        try {
            for(Car car: cars) {
                preparedStatement = connection.prepareStatement("SELECT * FROM Car WHERE id=?");
                preparedStatement.setInt(1, car.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    preparedStatement = connection.prepareStatement("UPDATE Car SET number = ?, model = ?, owner = ? WHERE id = ?");
                    preparedStatement.setString(1, car.getNumber());
                    preparedStatement.setString(2, car.getModel());
                    preparedStatement.setString(3, car.getOwner());
                    preparedStatement.setInt(4, car.getId());
                    preparedStatement.executeUpdate();
                }
                else{
                    preparedStatement = connection.prepareStatement("INSERT INTO Car VALUES(?, ?, ?, ?)");
                    preparedStatement.setInt(1, car.getId());
                    preparedStatement.setString(2, car.getNumber());
                    preparedStatement.setString(3, car.getModel());
                    preparedStatement.setString(4, car.getOwner());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void conductAudit(Car oldCar, Car newCar, String action) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CarAudit(id_car, old_number, new_number, old_model," +
                " new_model, old_owner, new_owner, type_of_edit, date_edit)" +
                " VALUES(?,?,?,?,?,?,?,?,?)");
        preparedStatement.setInt(1, newCar.getId());
        preparedStatement.setString(2, action.equals("Добавлен")? "": oldCar.getNumber());
        preparedStatement.setString(3, action.equals("Удален")?"":newCar.getNumber());
        preparedStatement.setString(4, action.equals("Добавлен")? "":oldCar.getModel());
        preparedStatement.setString(5, action.equals("Удален")?"":newCar.getModel());
        preparedStatement.setString(6, action.equals("Добавлен")? "":oldCar.getOwner());
        preparedStatement.setString(7, action.equals("Удален")?"":newCar.getOwner());
        preparedStatement.setString(8, action);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        preparedStatement.setString(9, dateFormat.format(date));

        preparedStatement.executeUpdate();
    }

    @Override
    public List<CarAudit> getAudit(int id) {
        List<CarAudit> auditTable = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CarAudit WHERE id_car=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                CarAudit auditRow = new CarAudit();
                auditRow.setId(resultSet.getInt("id"));
                auditRow.setId_car(resultSet.getInt("id_car"));
                auditRow.setOldNumber(resultSet.getString("old_number"));
                auditRow.setNewNumber(resultSet.getString("new_number"));
                auditRow.setOldModel(resultSet.getString("old_model"));
                auditRow.setNewModel(resultSet.getString("new_model"));
                auditRow.setOldOwner(resultSet.getString("old_owner"));
                auditRow.setNewOwner(resultSet.getString("new_owner"));
                auditRow.setTypeOfEdit(resultSet.getString("type_of_edit"));
                auditRow.setDateEdit(resultSet.getString("date_edit"));
                auditTable.add(auditRow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return auditTable;
    }

}
