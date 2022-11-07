package by.javafx.petrovich.demo.dao.impl;

import by.javafx.petrovich.demo.dao.EmployeeDao;
import by.javafx.petrovich.demo.model.Employee;
import by.javafx.petrovich.demo.util.DateBaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.javafx.petrovich.demo.dao.ColumnNames.ID_EMPLOYEE;
import static by.javafx.petrovich.demo.dao.ColumnNames.PERSONNEL_NUMBER;
import static by.javafx.petrovich.demo.dao.ColumnNames.NAME;
import static by.javafx.petrovich.demo.dao.ColumnNames.SURNAME;

public class EmployeeDaoImpl implements EmployeeDao {
    private DateBaseUtil dateBaseUtil = new DateBaseUtil();
    private static final String PERCENT_SIGN = "%";
    private static final String SQL_READ_ALL_EMPLOYEE = "SELECT id_employee, personnel_number, name, surname FROM employeesort.employees;";
    private static final String SQL_READ_EMPLOYEE_BY_ID = "SELECT id_employee, personnel_number, name, surname " +
            "FROM employees where id_employee = ?";
    private static final String SQL_READ_EMPLOYEE_BY_PERSONNEL_NUMBER = "SELECT id_employee, personnel_number, name, surname " +
            "FROM employees where personnel_number = ?";
    private static final String SQL_READ_EMPLOYEE_BY_NAME = "SELECT id_employee, personnel_number, name, surname " +
            "FROM employees where name like ?";
    private static final String SQL_READ_EMPLOYEE_BY_SURNAME = "SELECT id_employee, personnel_number,name,surname " +
            "FROM employees where surname like ?";

    /**
     * @return
     */
    @Override
    public ObservableList<Employee> receiveAllEmployee() {
        Connection connection = dateBaseUtil.receiveConnection();
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

    /**
     * @param id
     * @return
     */
    @Override
    public ObservableList<Employee> receiveEmployeeById(int id) {
        Connection connection = dateBaseUtil.receiveConnection();
        ObservableList<Employee> allEmployeesById = FXCollections.observableArrayList();
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
                allEmployeesById.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allEmployeesById;
    }

    /**
     * @param personnelNumber
     * @return
     */
    @Override
    public ObservableList<Employee> receiveEmployeeByPersonnelNumber(int personnelNumber) {
        Connection connection = dateBaseUtil.receiveConnection();
        ObservableList<Employee> employeeByPersonnelNumber = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_PERSONNEL_NUMBER);
            preparedStatement.setInt(1, personnelNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt(ID_EMPLOYEE));
                employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER));
                employee.setName(resultSet.getString(NAME));
                employee.setSurname(resultSet.getString(SURNAME));
                employeeByPersonnelNumber.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeByPersonnelNumber;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public ObservableList<Employee> receiveEmployeeByName(String name) {
        Connection connection = dateBaseUtil.receiveConnection();
        ObservableList<Employee> employeesByName = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_NAME);
            preparedStatement.setString(1, name + PERCENT_SIGN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt(ID_EMPLOYEE));
                employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER));
                employee.setName(resultSet.getString(NAME));
                employee.setSurname(resultSet.getString(SURNAME));
                employeesByName.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeesByName;
    }

    /**
     * @param surname
     * @return
     */
    @Override
    public ObservableList<Employee> receiveEmployeeBySurname(String surname) {
        Connection connection = dateBaseUtil.receiveConnection();
        ObservableList<Employee> employeeBySurname = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_SURNAME);
            preparedStatement.setString(1, surname + PERCENT_SIGN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setIdEmployees(resultSet.getInt(ID_EMPLOYEE));
                employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER));
                employee.setName(resultSet.getString(NAME));
                employee.setSurname(resultSet.getString(SURNAME));
                employeeBySurname.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeBySurname;
    }

}