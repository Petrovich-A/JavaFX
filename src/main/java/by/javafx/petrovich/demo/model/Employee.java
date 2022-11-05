package by.javafx.petrovich.demo.model;

import java.util.Objects;

public class Employee {
    int idEmployees;
    int personnelNumber;
    String name;
    String surname;

    public Employee() {
    }

    public Employee(int idEmployees, int personnelNumber, String name, String surname) {
        this.idEmployees = idEmployees;
        this.personnelNumber = personnelNumber;
        this.name = name;
        this.surname = surname;
    }

    public int getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(int idEmployees) {
        this.idEmployees = idEmployees;
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
        return idEmployees == employee.idEmployees && personnelNumber == employee.personnelNumber && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployees, personnelNumber, name, surname);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("idEmployees=").append(idEmployees);
        sb.append(", personnelNumber=").append(personnelNumber);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}