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
    ObservableList<Employee> findAllEmployees();

    /**
     * Receives list of Employees with definite id.
     *
     * @param id
     * @return <code>Employee employeeByPersonnelNumber;</code>
     */
    Employee findEmployeeById(int id);

    /**
     * Receives list of Employees with definite personnelNumber.
     *
     * @param personnelNumber
     * @return <code>Employee employeeByPersonnelNumber;</code>
     */
    Employee findEmployeeByPersonnelNumber(int personnelNumber);

    /**
     * Receives list of Employees with definite name.
     *
     * @param name
     * @return <code>ObservableList<Employee> employeesByName;</code>
     */
    ObservableList<Employee> findEmployeesByName(String name);

    /**
     * Receives list of Employees with definite surname.
     *
     * @param surname
     * @return <code>ObservableList<Employee> employeeBySurname;</code>
     */
    ObservableList<Employee> findEmployeesBySurname(String surname);

}
