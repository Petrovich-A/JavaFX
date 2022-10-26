package by.javafx.petrovich.demo.controller;

import by.javafx.petrovich.demo.model.Employee;
import by.javafx.petrovich.demo.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ShowEmployeesController implements Initializable {
    @FXML
    private TableColumn<Employee, Integer> column_id_employee;
    @FXML
    private TableColumn<Employee, String> column_name;
    @FXML
    private TableView<Employee> table;
    @FXML
    private Label welcomeText;
    private static final String CONNECTION_LINK = "jdbc:mysql://localhost:3306/employeesort";

    ObservableList<Employee> listEmployee;
    int index = -1;

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        column_id_employee.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("idEmployees"));
        column_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        listEmployee = DBUtil.getDataEmployee();
        table.setItems(listEmployee);
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}