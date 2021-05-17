package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Violation;import com.netcracker.web.violations.model.ViolationOutput;
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
            String SQL = "INSERT INTO Violation (date, status, address, id_fine, id_car) VALUES('" + violation.getDate() +
                    "'," + violation.getStatus() + ",'" + violation.getAddress() + "'," + violation.getId_fine() +
                    "," + violation.getId_car() + ")";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Violation violation) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
            "UPDATE Violation SET date =?, status=?, address=?, id_fine=?, id_car=? WHERE id=?");
            preparedStatement.setDate(1, violation.getDate());
            preparedStatement.setInt(2, violation.getStatus());
            preparedStatement.setString(3, violation.getAddress());
            preparedStatement.setInt(4, violation.getId_fine());
            preparedStatement.setInt(5, violation.getId_car());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Violation get(int id) {
        Violation violation = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Violation WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            violation = new Violation();

            violation.setId(resultSet.getInt("id"));
            violation.setDate(resultSet.getDate("date"));
            violation.setAddress(resultSet.getString("address"));
            violation.setStatus(resultSet.getInt("status"));
            violation.setId_fine(resultSet.getInt("id_fine"));
            violation.setId_car(resultSet.getInt("id_car"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return violation;
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
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Violation WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    @Override
    public ViolationOutput convertToOutput(Violation violation){
        ViolationOutput output = new ViolationOutput();
        output.setId(violation.getId());
        output.setDate(violation.getDate());
        output.setAddress(violation.getAddress());
        output.setStatus(violation.getStatus());

        //находим данные из связанных таблиц
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT number FROM Car WHERE id = " + violation.getId_car();
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            output.setCarNumber(resultSet.getString("number"));

            SQL = "SELECT type, amount FROM Fine WHERE id = " + violation.getId_fine();
            resultSet = statement.executeQuery(SQL);
            resultSet.next();
            output.setFineType(resultSet.getString("type"));
            output.setFineAmount(resultSet.getInt("amount"));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }

    @Override
    public Violation outputToViolation(ViolationOutput output){
        Violation violation = new Violation();
        violation.setId(output.getId());
        violation.setDate(output.getDate());
        violation.setAddress(output.getAddress());
        violation.setStatus(output.getStatus());

        //находим данные из связанных таблиц
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT id FROM Car WHERE number = '" + output.getCarNumber()+ "'";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            violation.setId_car(resultSet.getInt("id"));

            Statement statement1 = connection.createStatement();
            SQL = "SELECT id FROM Fine WHERE type = '" + output.getFineType() + "'";
            ResultSet resultSet1 = statement1.executeQuery(SQL);
            resultSet1.next();
            violation.setId_fine(resultSet1.getInt("id"));
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return violation;
    }
}
