package by.javafx.petrovich.demo.dao;

import by.javafx.petrovich.demo.model.Employee;
import javafx.collections.ObservableList;

public interface EmployeeDao {

    ObservableList<Employee> receiveAllEmployee();

    ObservableList<Employee> receiveEmployeeById(int id);

    ObservableList<Employee> receiveEmployeeByPersonnelNumber(int personnelNumber);

    ObservableList<Employee> receiveEmployeeByName(String name);

    ObservableList<Employee> receiveEmployeeBySurname(String surname);


}
