package by.javafx.petrovich.demo.util;

import by.javafx.petrovich.demo.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static by.javafx.petrovich.demo.dao.ColumnNames.ID_EMPLOYEE;
import static by.javafx.petrovich.demo.dao.ColumnNames.PERSONNEL_NUMBER;
import static by.javafx.petrovich.demo.dao.ColumnNames.NAME;
import static by.javafx.petrovich.demo.dao.ColumnNames.SURNAME;


public class DBUtil implements Initializable {
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_LINK = "jdbc:mysql://localhost:3306/employeesort";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "HzaArk_XnsS";
    private static final String SQL_READ_ALL_EMPLOYEE = "SELECT id_employee, personnel_number, name, surname FROM employeesort.employees;";
    private static final String SQL_READ_EMPLOYEE_BY_ID = "SELECT id_employee, personnel_number, name, surname FROM employees where id_employee = ?";
    private static final String SQL_READ_EMPLOYEE_BY_NAME = "SELECT id_employee, personnel_number, name, surname FROM employees where name = ?";


    public static Connection ConnectDd() {
        try {
            Class.forName(JDBC_DRIVER_NAME);
            Connection connection = DriverManager.getConnection(CONNECTION_LINK, USER_NAME, PASSWORD);
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

    public static ObservableList<Employee> receiveAllEmployee() {
        Connection connection = ConnectDd();
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_ALL_EMPLOYEE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt(ID_EMPLOYEE));
                employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER));
                employee.setName(resultSet.getString(NAME));
                employee.setSurname(resultSet.getString(SURNAME));
                allEmployees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployees;
    }

    public static ObservableList<Employee> receiveEmployeeById(int id) {
        Connection connection = ConnectDd();
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt(ID_EMPLOYEE));
                employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER));
                employee.setName(resultSet.getString(NAME));
                employee.setSurname(resultSet.getString(SURNAME));
                allEmployees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployees;
    }

    public static ObservableList<Employee> receiveEmployeeByName(String name) {
        Connection connection = ConnectDd();
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt(ID_EMPLOYEE));
                employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER));
                employee.setName(resultSet.getString(NAME));
                employee.setSurname(resultSet.getString(SURNAME));
                allEmployees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployees;
    }


}
