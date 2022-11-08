package by.javafx.petrovich.demo.controller;

import by.javafx.petrovich.demo.dao.impl.EmployeeDaoImpl;
import by.javafx.petrovich.demo.model.Employee;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static by.javafx.petrovich.demo.controller.AlertMessages.*;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.*;
import static by.javafx.petrovich.demo.controller.ChoiceBoxItemNames.*;
import static by.javafx.petrovich.demo.controller.EmployeeFieldsNames.*;

/**
 * @author Petrovich A.V.
 */
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
    private TextField text_field;
    private final Employee employee = new Employee();


    private EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Employee> listEmployee;
        ArrayList<String> choiceBoxItemNames = initializeChoiceBoxItems();
        column_id.setCellValueFactory(new PropertyValueFactory<>(ID_EMPLOYEE));
        column_personnel_number.setCellValueFactory(new PropertyValueFactory<>(PERSONNEL_NUMBER));
        column_name.setCellValueFactory(new PropertyValueFactory<>(NAME));
        column_surname.setCellValueFactory(new PropertyValueFactory<>(SURNAME));

        listEmployee = employeeDaoImpl.receiveAllEmployee();
        table.setItems(listEmployee);
        choiceBoxItemNames.forEach((itemName) -> choice_box.getItems().add(itemName));
    }

    /**
     *
     */
    @FXML
    protected void onFindButtonClick() {
        ObservableList<Employee> listEmployee;
        String selectedItem;
        String searchKeyWord;
        try {
            selectedItem = choice_box.getSelectionModel().getSelectedItem();
            searchKeyWord = text_field.getText();
            if (selectedItem == null) {
                showAlert(NO_CHOICE_ITEM, Alert.AlertType.WARNING, WARNING);
                return;
            }
            if (searchKeyWord.isEmpty()) {
                showAlert(NO_INPUT_VALUE, Alert.AlertType.WARNING, WARNING);
                return;
            }
            switch (selectedItem) {
                case (ID_ITEM) -> {
                    listEmployee = employeeDaoImpl.receiveEmployeeById(Integer.parseInt(searchKeyWord));
                    putItems(listEmployee);
                }
                case (PERSONNEL_NUMBER_ITEM) -> {
                    listEmployee = employeeDaoImpl.receiveEmployeeByPersonnelNumber(Integer.parseInt(searchKeyWord));
                    putItems(listEmployee);
                }
                case (NAME_ITEM) -> {
                    listEmployee = employeeDaoImpl.receiveEmployeeByName(searchKeyWord);
                    putItems(listEmployee);
                }
                case (SURNAME_ITEM) -> {
                    listEmployee = employeeDaoImpl.receiveEmployeeBySurname(searchKeyWord);
                    putItems(listEmployee);
                }
                default -> {
                }
            }
        } catch (NumberFormatException e) {
            showAlert(INPUT_NUMBER, Alert.AlertType.WARNING, ERROR);
        } catch (Exception e) {
            showAlert(CHOICE_AND_FILL, Alert.AlertType.ERROR, INFORMATION);
        }
    }

    private ArrayList<String> initializeChoiceBoxItems() {
        ArrayList<String> choiceBoxItemNames = new ArrayList<>();
        choiceBoxItemNames.add(ID_ITEM);
        choiceBoxItemNames.add(PERSONNEL_NUMBER_ITEM);
        choiceBoxItemNames.add(NAME_ITEM);
        choiceBoxItemNames.add(SURNAME_ITEM);
        return choiceBoxItemNames;
    }

    private void putItems(ObservableList<Employee> listEmployee) {
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

}