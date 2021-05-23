package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FineDAOImpl implements FineDAO {

    private static String url = "jdbc:postgresql://localhost:5432/Violations";
    private static String username = "postgres";
    private static String password = "Vegetable*1";
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
    public void save(Fine fine) {
        try {
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Fine (type, amount) VALUES(?,?)");

            preparedStatement.setString(1, fine.getType());
            preparedStatement.setInt(2, fine.getAmount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Fine fine) {

    }

    @Override
    public Fine get(int id) {
        Fine fine = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Fine WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            fine = new Fine();

            fine.setId(resultSet.getInt("id"));
            fine.setType(resultSet.getString("type"));
            fine.setAmount(resultSet.getInt("amount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fine;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Fine> allFines() {
        List<Fine> fines = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Fine";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Fine fine = new Fine();

                fine.setId(resultSet.getInt("id"));
                fine.setType(resultSet.getString("type"));
                fine.setAmount(resultSet.getInt("amount"));

                fines.add(fine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fines;
    }
}
