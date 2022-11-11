package by.javafx.petrovich.demo.dao.impl;

import by.javafx.petrovich.demo.dao.EmployeeDao;
import by.javafx.petrovich.demo.model.Employee;
import by.javafx.petrovich.demo.util.DateBaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.javafx.petrovich.demo.controller.FieldNames.ID;
import static by.javafx.petrovich.demo.controller.FieldNames.NAME;
import static by.javafx.petrovich.demo.controller.FieldNames.PERSONNEL_NUMBER;
import static by.javafx.petrovich.demo.controller.FieldNames.SURNAME;

/**
 * @author Petrovich A.V.
 */
public class EmployeeDaoImpl implements EmployeeDao {
    private DateBaseUtil dateBaseUtil = new DateBaseUtil();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PERCENT_SIGN = "%";
    private static final String SQL_READ_ALL_EMPLOYEE = "SELECT id_employee, personnel_number, name, surname " +
            "FROM employeesort.employees;";
    private static final String SQL_READ_EMPLOYEE_BY_ID = "SELECT id_employee, personnel_number, name, surname " +
            "FROM employees where id_employee = ?";
    private static final String SQL_READ_EMPLOYEE_BY_PERSONNEL_NUMBER = "SELECT id_employee, personnel_number, name, surname " +
            "FROM employees where personnel_number = ?";
    private static final String SQL_READ_EMPLOYEE_BY_NAME = "SELECT id_employee, personnel_number, name, surname " +
            "FROM employees where name like ?";
    private static final String SQL_READ_EMPLOYEE_BY_SURNAME = "SELECT id_employee, personnel_number,name,surname " +
            "FROM employees where surname like ?";


    @Override
    public ObservableList<Employee> receiveAllEmployee() {
        ObservableList<Employee> allEmployees;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_ALL_EMPLOYEE);
            ResultSet resultSet = preparedStatement.executeQuery();
            allEmployees = putEmployees(resultSet);
            LOGGER.log(Level.INFO, String.format("Reading all Employees from date base have done successfully. " +
                    "allEmployees: %s", allEmployees));
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't receive all employees from date base.", e));
        }
        return allEmployees;
    }


    @Override
    public ObservableList<Employee> receiveEmployeeById(int id) {
        ObservableList<Employee> employeesById;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeesById = putEmployees(resultSet);
            LOGGER.log(Level.INFO, String.format("Reading Employee from date base with id %s have done successfully. " +
                    "Employee: %s.", id, employeesById));
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't receive employee with id %s from date base.", id, e));
        }
        return employeesById;
    }

    @Override
    public ObservableList<Employee> receiveEmployeeByPersonnelNumber(int personnelNumber) {
        ObservableList<Employee> employeeByPersonnelNumber;
        try (Connection connection = dateBaseUtil.receiveConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_PERSONNEL_NUMBER);
            preparedStatement.setInt(1, personnelNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeByPersonnelNumber = putEmployees(resultSet);
            LOGGER.log(Level.INFO, String.format("Reading Employee from date base with personnelNumber %s have done successfully. " +
                    "Employee: %s.", personnelNumber, employeeByPersonnelNumber));
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't receive employee with personnelNumber %s from date base.", personnelNumber, e));
        }
        return employeeByPersonnelNumber;
    }

    @Override
    public ObservableList<Employee> receiveEmployeeByName(String name) {
        ObservableList<Employee> employeesByName;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_NAME);
            preparedStatement.setString(1, name + PERCENT_SIGN);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeesByName = putEmployees(resultSet);
            LOGGER.log(Level.INFO, String.format("Reading Employee from date base with name %s have done successfully. " +
                    "Employee: %s.", name, employeesByName));
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't receive employee with name %s from date base.", name, e));
        }
        return employeesByName;
    }

    @Override
    public ObservableList<Employee> receiveEmployeeBySurname(String surname) {
        ObservableList<Employee> employeeBySurname;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_READ_EMPLOYEE_BY_SURNAME);
            preparedStatement.setString(1, surname + PERCENT_SIGN);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeBySurname = putEmployees(resultSet);
            LOGGER.log(Level.INFO, String.format("Reading Employee from date base with name %s have done successfully. " +
                    "Employee: %s.", surname, employeeBySurname));
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't receive employee with surname %s from date base.", surname, e));
        }
        return employeeBySurname;
    }
    /**
     * Builds Employee entities received resulting dataset using resultSet and places them to the <code>ObservableList<Employee></code>.
     *
     * @param resultSet
     * @return <code>ObservableList<Employee> employees = FXCollections.observableArrayList();</code>
     */
    private ObservableList<Employee> putEmployees(ResultSet resultSet) throws SQLException {
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setIdEmployee(resultSet.getInt(ID.getDateBaseColumnNames()));
            employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER.getDateBaseColumnNames()));
            employee.setName(resultSet.getString(NAME.getDateBaseColumnNames()));
            employee.setSurname(resultSet.getString(SURNAME.getDateBaseColumnNames()));
            employees.add(employee);
        }
        return employees;
    }
}