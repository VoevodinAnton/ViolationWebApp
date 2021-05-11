package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViolationsDAOImpl implements ViolationDAO{

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
    public void save(Violation violation) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Violation (id, date, status, address, id_fine, id_car) VALUES('" + violation.getDate() +
                    "'," + violation.getStatus() + ",'" + violation.getAddress() + "'," + violation.getId_fine() +
                    "," + violation.getId_car() + ")";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Violation violation) {

    }

    @Override
    public Violation get(int id) {
        return null;
    }

    @Override
    public Violation getByCar(int idCar) {
        return null;
    }

    @Override
    public Violation getByFine(int idFine) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Violation> allViolations() {
        List<Violation> violations = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Violation";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Violation violation = new Violation();
                violation.setId(resultSet.getInt("id"));
                violation.setDate(resultSet.getDate("date"));
                violation.setStatus(resultSet.getInt("status"));
                violation.setAddress(resultSet.getString("address"));
                violation.setId_car(resultSet.getInt("id_car"));
                violation.setId_fine(resultSet.getInt("id_fine"));


                violations.add(violation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return violations;
    }
}
