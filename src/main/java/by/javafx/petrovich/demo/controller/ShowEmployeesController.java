package by.javafx.petrovich.demo.controller;

import by.javafx.petrovich.demo.model.Employee;
import by.javafx.petrovich.demo.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    @FXML
    private TableView<Employee> table;
    @FXML
    private TableColumn<Employee, Integer> column_id_employee;
    @FXML
    private TableColumn<Employee, String> column_name;
    @FXML
    private ChoiceBox<String> choice_box;
    @FXML
    private Button buttonFind;
    @FXML
    private TextField text_field;
    private final Employee employee = new Employee();

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Employee> listEmployee;
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
    protected void onFindButtonClick(ActionEvent event) {
        ObservableList<Employee> listEmployee = FXCollections.observableArrayList();
        String selectedItem;
        String searchKeyWord;
        try {
           selectedItem = choice_box.getSelectionModel().getSelectedItem();
           searchKeyWord = text_field.getText();
            switch (selectedItem) {
                case ("idEmployees"):
                    try {
                        listEmployee = DBUtil.getEmployeeById(Integer.valueOf(searchKeyWord));
                    } catch (NumberFormatException e) {
                        String alertMessage = "enter a number";
                        String title = "ERROR";
                        showAlert(alertMessage, Alert.AlertType.WARNING, title);
                    }
                    setList(listEmployee);
                    break;
                case ("name"):
                    listEmployee = DBUtil.getEmployeeByName(searchKeyWord);
                    setList(listEmployee);
                    break;
                }
        } catch (Exception e) {
            String alertMessage = "choice an item and fill a text field";
            String title = "INFORMATION";
            showAlert(alertMessage, Alert.AlertType.INFORMATION, title);
        }
    }

    private void setList(ObservableList<Employee> listEmployee) {
        String alertMessage = "no results";
        String title = "INFORMATION";
        if (!listEmployee.isEmpty()) {
            table.setItems(listEmployee);
        } else {
            showAlert(alertMessage, Alert.AlertType.INFORMATION, title);
        }
    }

    private void showAlert(String alertMessage, Alert.AlertType alertType, String title) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

}