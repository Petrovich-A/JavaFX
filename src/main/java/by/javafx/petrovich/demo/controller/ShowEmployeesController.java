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
import java.util.ResourceBundle;

import static by.javafx.petrovich.demo.controller.AlertMessages.INPUT_NUMBER;
import static by.javafx.petrovich.demo.controller.AlertMessages.CHOICE_AND_FILL;
import static by.javafx.petrovich.demo.controller.AlertMessages.NO_RESULTS;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.ERROR;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.INFORMATION;

public class ShowEmployeesController implements Initializable {
    @FXML
    private TableView<Employee> table;
    @FXML
    private TableColumn<Employee, Integer> column_id;
    @FXML
    private TableColumn<Employee, Integer> column_personnel_number;
    @FXML
    private TableColumn<Employee, String> column_name;
    @FXML
    private TableColumn<Employee, String> column_surname;
    @FXML
    private ChoiceBox<String> choice_box;
    @FXML
    private Button buttonFind;
    @FXML
    private TextField text_field;
    private final Employee employee = new Employee();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Employee> listEmployee;
        Field[] fields = getFields();
        column_id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("idEmployees"));
        column_personnel_number.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("personnelNumber"));
        column_name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        column_surname.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));

        listEmployee = DBUtil.receiveAllEmployee();
        table.setItems(listEmployee);
        for (Field field : fields) {
            choice_box.getItems().add(field.getName());
        }
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
                        listEmployee = DBUtil.receiveEmployeeById(Integer.valueOf(searchKeyWord));
                        setList(listEmployee);
                        break;
                    } catch (NumberFormatException e) {
                        showAlert(INPUT_NUMBER, Alert.AlertType.WARNING, ERROR);
//                        LablesField.ID.getFieldNameDB();
                    }
                case ("personnelNumber"):
                    try {
                        listEmployee = DBUtil.receiveEmployeeByPersonnelNumber(Integer.valueOf(searchKeyWord));
                        setList(listEmployee);
                        break;
                    } catch (NumberFormatException e) {
                        showAlert(INPUT_NUMBER, Alert.AlertType.WARNING, ERROR);
                    }
                case ("name"):
                    listEmployee = DBUtil.receiveEmployeeByName(searchKeyWord);
                    setList(listEmployee);
                    break;
                case ("surname"):
                    listEmployee = DBUtil.receiveEmployeeBySurname(searchKeyWord);
                    setList(listEmployee);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            showAlert(CHOICE_AND_FILL, Alert.AlertType.ERROR, INFORMATION);
        }
    }

    private void setList(ObservableList<Employee> listEmployee) {
        if (!listEmployee.isEmpty()) {
            table.setItems(listEmployee);
        } else {
            showAlert(NO_RESULTS, Alert.AlertType.INFORMATION, INFORMATION);
        }
    }

    private void showAlert(String alertMessage, Alert.AlertType alertType, String title) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    private Field[] getFields() {
        Class<? extends Employee> clazz = employee.getClass();
        Field[] fields = clazz.getDeclaredFields();
        return fields;
    }

}