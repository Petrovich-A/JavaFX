package by.javafx.petrovich.demo.controller;

import by.javafx.petrovich.demo.model.Employee;
import by.javafx.petrovich.demo.util.DBUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ShowEmployeesController implements Initializable {
    private static final String CONNECTION_LINK = "jdbc:mysql://localhost:3306/employeesort";
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Employee> table;
    @FXML
    private TableColumn<Employee, Integer> column_id_employee;
    @FXML
    private TableColumn<Employee, String> column_name;
    @FXML
    private ChoiceBox<String> choice_box;
    private final Employee employee = new Employee();

    ObservableList<Employee> listEmployee;

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Field[] fields = getFields();
        column_id_employee.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("idEmployees"));
        column_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        listEmployee = DBUtil.getDataEmployee();
        table.setItems(listEmployee);
        for (Field field : fields) {
            choice_box.getItems().add(field.getName());
        }
    }

    private Field[] getFields() {
        Class<? extends Employee> clazz = employee.getClass();
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


}