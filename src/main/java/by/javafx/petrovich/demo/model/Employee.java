package by.javafx.petrovich.demo.model;

import java.util.Objects;

public class Employee {
    int idEmployees;
    String name;

    public Employee(int idEmployees, String name) {
        this.idEmployees = idEmployees;
        this.name = name;
    }

    public Employee() {
    }

    public int getIdEmployees() {
        return idEmployees;
    }

    public void setIdEmployees(int idEmployees) {
        this.idEmployees = idEmployees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return idEmployees == employee.idEmployees && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployees, name);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Employee{");
        sb.append("idEmployees=").append(idEmployees);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
