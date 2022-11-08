package by.javafx.petrovich.demo.model;

import java.util.Objects;

public class Employee {
    int idEmployee;
    int personnelNumber;
    String name;
    String surname;

    public Employee() {
    }

    public Employee(int idEmployee, int personnelNumber, String name, String surname) {
        this.idEmployee = idEmployee;
        this.personnelNumber = personnelNumber;
        this.name = name;
        this.surname = surname;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return idEmployee == employee.idEmployee && personnelNumber == employee.personnelNumber && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, personnelNumber, name, surname);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("idEmployee=").append(idEmployee);
        sb.append(", personnelNumber=").append(personnelNumber);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}