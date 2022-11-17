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
    private final DateBaseUtil dateBaseUtil = new DateBaseUtil();
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PERCENT_SIGN = "%";
    private static final String SELECT_ALL = "SELECT id_employee, personnel_number, name, surname ";
    private static final String FROM = "FROM employees ";
    private static final String WHERE_ID = "where id_employee = ?";
    private static final String WHERE_PERSONNEL_NUMBER = "where personnel_number = ?";
    private static final String WHERE_NAME = "where name like ?";
    private static final String WHERE_SURNAME = "where surname like ?";

    @Override
    public ObservableList<Employee> findAllEmployees() {
        ObservableList<Employee> allEmployees;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + FROM);
            ResultSet resultSet = preparedStatement.executeQuery();
            allEmployees = employeesMapper(resultSet);
            LOGGER.log(Level.INFO, "Reading all Employees from date base have done successfully. " +
                    "allEmployees: {0}", allEmployees);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't find all employees from date base. %s", e));
        }
        return allEmployees;
    }


    @Override
    public Employee findEmployeeById(int id) {
        Employee employeeById;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + FROM + WHERE_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeById = employeeMapper(resultSet);
            LOGGER.log(Level.INFO, "Reading Employee from date base with id {0} have done successfully. " +
                    "Employee: {1}.", id, employeeById);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't find employee with id %d from date base. %s", id, e));
        }
        return employeeById;
    }

    @Override
    public Employee findEmployeeByPersonnelNumber(int personnelNumber) {
        Employee employeeByPersonnelNumber;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + FROM + WHERE_PERSONNEL_NUMBER);
            preparedStatement.setInt(1, personnelNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeByPersonnelNumber = employeeMapper(resultSet);
            LOGGER.log(Level.INFO, "Reading Employee from date base with personnelNumber {0} have done successfully. " +
                    "Employee: {1}.", personnelNumber, employeeByPersonnelNumber);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't find employee with personnelNumber %s from date base. %s", personnelNumber, e));
        }
        return employeeByPersonnelNumber;
    }

    @Override
    public ObservableList<Employee> findEmployeesByName(String name) {
        ObservableList<Employee> employeesByName;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + FROM + WHERE_NAME);
            preparedStatement.setString(1, name + PERCENT_SIGN);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeesByName = employeesMapper(resultSet);
            LOGGER.log(Level.INFO, "Reading Employee from date base with name {0} have done successfully. " +
                    "Employee: {1}.", name, employeesByName);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't find employee with name %s from date base. %s", name, e));
        }
        return employeesByName;
    }

    @Override
    public ObservableList<Employee> findEmployeesBySurname(String surname) {
        ObservableList<Employee> employeeBySurname;
        try (Connection connection = dateBaseUtil.receiveConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL + FROM +WHERE_SURNAME);
            preparedStatement.setString(1, surname + PERCENT_SIGN);
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeBySurname = employeesMapper(resultSet);
            LOGGER.log(Level.INFO, "Reading Employee from date base with name {0} have done successfully. " +
                    "Employee: {1}.", surname, employeeBySurname);
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Can't find employee with surname %s from date base. %s", surname, e));
        }
        return employeeBySurname;
    }
    /**
     * Builds Employee entities received resulting dataset using resultSet and places them to the <code>ObservableList<Employee></code>.
     *
     * @return <code>ObservableList<Employee> employees = FXCollections.observableArrayList();</code>
     */
    private ObservableList<Employee> employeesMapper(ResultSet resultSet) throws SQLException {
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

    private Employee employeeMapper(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        while (resultSet.next()) {
            employee.setIdEmployee(resultSet.getInt(ID.getDateBaseColumnNames()));
            employee.setPersonnelNumber(resultSet.getInt(PERSONNEL_NUMBER.getDateBaseColumnNames()));
            employee.setName(resultSet.getString(NAME.getDateBaseColumnNames()));
            employee.setSurname(resultSet.getString(SURNAME.getDateBaseColumnNames()));
        }
        return employee;
    }
}