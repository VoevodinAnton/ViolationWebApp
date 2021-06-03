package com.netcracker.web.violations.dao;

import com.netcracker.web.violations.model.Car;
import com.netcracker.web.violations.model.Fine;
import com.netcracker.web.violations.model.Violation;
import com.netcracker.web.violations.model.ViolationOutput;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ViolationsDAOImpl implements ViolationDAO {

    private static String url = "jdbc:postgresql://localhost:5432/violations";
    private static String username = "postgres";
    private static String password = "avoeva";
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
    public void save(Violation violation) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Violation (date, status, address, id_fine, id_car) VALUES(TO_DATE(?, 'YYYY-MM-DD'),?,?,?,?)");

            preparedStatement.setString(1, violation.getDate());
            preparedStatement.setInt(2, violation.getStatus());
            preparedStatement.setString(3, violation.getAddress());
            preparedStatement.setInt(4, violation.getId_fine());
            preparedStatement.setInt(5, violation.getId_car());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Violation violation) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Violation SET date = TO_DATE(?, 'YYYY-MM-DD'), status=?, address=?, id_fine=?, id_car=? WHERE id=?");
            preparedStatement.setString(1, violation.getDate());
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
            violation = createViolation(resultSet);
            System.out.println(violation.getDate());

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
    public List<Violation> getAllViolations() {
        List<Violation> violations = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Violation";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Violation violation = createViolation(resultSet);
                violations.add(violation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return violations;
    }

    @Override
    public ViolationOutput convertToOutput(Violation violation) {
        ViolationOutput output = new ViolationOutput();
        output.setId(violation.getId());
        output.setDate(violation.getDate());
        output.setAddress(violation.getAddress());
        output.setStatus(violation.getStatus());

        //находим данные из связанных таблиц
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT number FROM Car WHERE id = ?");
            statement.setInt(1, violation.getId_car());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            output.setCarNumber(resultSet.getString("number"));

            statement = connection.prepareStatement("SELECT type, amount FROM Fine WHERE id =?");
            statement.setInt(1, violation.getId_fine());
            resultSet = statement.executeQuery();
            resultSet.next();
            output.setFineType(resultSet.getString("type"));
            output.setFineAmount(resultSet.getInt("amount"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }

    @Override
    public Violation outputToViolation(ViolationOutput output) {
        Violation violation = new Violation();
        violation.setId(output.getId());
        violation.setDate(output.getDate());
        violation.setAddress(output.getAddress());
        violation.setStatus(output.getStatus());

        //находим данные из связанных таблиц
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT id FROM Car WHERE number = '" + output.getCarNumber() + "'";
            ResultSet resultSet = statement.executeQuery(SQL);
            resultSet.next();
            violation.setId_car(resultSet.getInt("id"));

            Statement statement1 = connection.createStatement();
            SQL = "SELECT id FROM Fine WHERE type = '" + output.getFineType() + "'";
            ResultSet resultSet1 = statement1.executeQuery(SQL);
            resultSet1.next();
            violation.setId_fine(resultSet1.getInt("id"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return violation;
    }

    @Override
    public void importFromFile(List<Violation> violations) {
        PreparedStatement preparedStatement = null;
        try {
            for(Violation violation: violations) {
                preparedStatement = connection.prepareStatement("SELECT * FROM Violation WHERE id=?");
                preparedStatement.setInt(1, violation.getId());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    preparedStatement = connection.prepareStatement(
                            "UPDATE Violation SET date = TO_DATE(?, 'YYYY-MM-DD'), status=?, address=?, id_fine=?, id_car=? WHERE id=?");
                    preparedStatement.setString(1, violation.getDate());
                    preparedStatement.setInt(2, violation.getStatus());
                    preparedStatement.setString(3, violation.getAddress());
                    preparedStatement.setInt(4, violation.getId_fine());
                    preparedStatement.setInt(5, violation.getId_car());
                    preparedStatement.setInt(6, violation.getId());
                    preparedStatement.executeUpdate();
                }
                else{
                    preparedStatement = connection.prepareStatement("INSERT INTO Violation VALUES(?,TO_DATE(?, 'YYYY-MM-DD'),?,?,?,?)");

                    preparedStatement.setInt(1, violation.getId());
                    preparedStatement.setString(2, violation.getDate());
                    preparedStatement.setInt(3, violation.getStatus());
                    preparedStatement.setString(4, violation.getAddress());
                    preparedStatement.setInt(5, violation.getId_fine());
                    preparedStatement.setInt(6, violation.getId_car());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Violation> showViolations(int idCar) {
        List<Violation> violations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Violation WHERE id_car=?");

            preparedStatement.setInt(1, idCar);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Violation violation = new Violation();

                violation.setId(resultSet.getInt("id"));
                violation.setDate(resultSet.getDate("date").toString());
                violation.setStatus(resultSet.getInt("status"));
                violation.setAddress(resultSet.getString("address"));
                violation.setId_fine(resultSet.getInt("id_fine"));
                violation.setId_car(resultSet.getInt("id_car"));

                violations.add(violation);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return violations;

    }

    public List<Violation> searchViolation(Object... arg) {
        StringBuilder builder = new StringBuilder();
        List<Violation> violations = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            builder.append(arg[i] + " ");
            System.out.println(builder.toString());
        }

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT *  FROM car, violation, fine where violation.id_car = car.id and violation.id_fine = fine.id and to_tsvector( status ||' ' || date ||' ' || number ||' '|| type) @@ plainto_tsquery('" + builder.toString() + "')";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Violation violation = createViolation(resultSet);
                violations.add(violation);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return violations;

    }
     public Violation createViolation(ResultSet resultSet){
        Violation violation = new Violation();
        try {
            violation.setId(resultSet.getInt("id"));
            violation.setDate(resultSet.getDate("date").toString());
            violation.setStatus(resultSet.getInt("status"));
            violation.setAddress(resultSet.getString("address"));
            violation.setId_car(resultSet.getInt("id_car"));
            violation.setId_fine(resultSet.getInt("id_fine"));
            System.out.println(violation.getAddress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
         return violation;
     }
}
