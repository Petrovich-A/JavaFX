package by.javafx.petrovich.demo.dao;

import by.javafx.petrovich.demo.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    private static Employee receiveEmployee(ResultSet resultSet) throws SQLException {
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        Employee employee = null;
        while (resultSet.next()) {
            employee = new Employee();
            employee.setIdEmployees(resultSet.getInt("idEmployees"));
            employee.setName(resultSet.getString("name"));
            allEmployees.add(employee);
        }
        return employee;
    }

    private static ObservableList<Employee> receiveAllEmployees(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        ObservableList<Employee> allEmployees = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setIdEmployees(resultSet.getInt("idEmployees"));
            employee.setName(resultSet.getString("name"));
            allEmployees.add(employee);
        }
        return allEmployees;
    }

}
