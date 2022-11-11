package by.javafx.petrovich.demo.dao;

import by.javafx.petrovich.demo.model.Employee;
import javafx.collections.ObservableList;

/**
 * @author Petrovich A.V.
 */

public interface EmployeeDao {
    /**
     * Receives list of all Employees held in database.
     *
     * @return <code>ObservableList<Employee> allEmployees;</code>
     */
    ObservableList<Employee> receiveAllEmployee();

    /**
     * Receives list of Employees with definite id.
     *
     * @param id
     * @return <code>ObservableList<Employee> employeeByPersonnelNumber;</code>
     */
    ObservableList<Employee> receiveEmployeeById(int id);

    /**
     * Receives list of Employees with definite personnelNumber.
     *
     * @param personnelNumber
     * @return <code>ObservableList<Employee> employeeByPersonnelNumber;</code>
     */
    ObservableList<Employee> receiveEmployeeByPersonnelNumber(int personnelNumber);

    /**
     * Receives list of Employees with definite name.
     *
     * @param name
     * @return <code>ObservableList<Employee> employeesByName;</code>
     */
    ObservableList<Employee> receiveEmployeeByName(String name);

    /**
     * Receives list of Employees with definite surname.
     *
     * @param surname
     * @return <code>ObservableList<Employee> employeeBySurname;</code>
     */
    ObservableList<Employee> receiveEmployeeBySurname(String surname);

}
