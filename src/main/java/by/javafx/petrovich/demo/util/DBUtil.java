package by.javafx.petrovich.demo.util;

import by.javafx.petrovich.demo.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class DBUtil implements Initializable {
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_LINK = "jdbc:mysql://localhost:3306/employeesort";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "HzaArk_XnsS";

    public static Connection ConnectDd() {
        try {
            Class.forName(JDBC_DRIVER_NAME);
            Connection connection = DriverManager.getConnection(CONNECTION_LINK, USER_NAME, PASSWORD);
//            JOptionPan
            return connection;
        } catch (Exception e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public static ObservableList<Employee> getDataEmployee() {
        Connection connection = ConnectDd();
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idEmployees, name FROM employeesort.employees");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt("idEmployees"));
                employee.setName(resultSet.getString("name"));
                allEmployees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployees;
    }

    public static ObservableList<Employee> getEmployeeById(int id) {
        Connection connection = ConnectDd();
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idEmployees, name FROM employees where idEmployees = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt("idEmployees"));
                employee.setName(resultSet.getString("name"));
                allEmployees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployees;
    }

    public static ObservableList<Employee> getEmployeeByName(String name) {
        Connection connection = ConnectDd();
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT idEmployees, name FROM employees where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt("idEmployees"));
                employee.setName(resultSet.getString("name"));
                allEmployees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployees;
    }


}
