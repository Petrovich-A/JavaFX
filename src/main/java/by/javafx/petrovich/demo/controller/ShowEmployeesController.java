package by.javafx.petrovich.demo.controller;

import by.javafx.petrovich.demo.dao.impl.EmployeeDaoImpl;
import by.javafx.petrovich.demo.model.Employee;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.javafx.petrovich.demo.controller.AlertMessages.CHOICE_AND_FILL;
import static by.javafx.petrovich.demo.controller.AlertMessages.INPUT_NUMBER;
import static by.javafx.petrovich.demo.controller.AlertMessages.NO_CHOICE_ITEM;
import static by.javafx.petrovich.demo.controller.AlertMessages.NO_INPUT_VALUE;
import static by.javafx.petrovich.demo.controller.AlertMessages.NO_RESULTS;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.ERROR;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.INFORMATION;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.WARNING;
import static by.javafx.petrovich.demo.controller.FieldNames.ID;
import static by.javafx.petrovich.demo.controller.FieldNames.NAME;
import static by.javafx.petrovich.demo.controller.FieldNames.PERSONNEL_NUMBER;
import static by.javafx.petrovich.demo.controller.FieldNames.SURNAME;

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
    private EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();

    /**
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Employee> listEmployee;
        ArrayList<String> choiceBoxItemNames = initializeChoiceBoxItems();
        column_id.setCellValueFactory(new PropertyValueFactory<>(ID.getEmployeeClassFieldsNames()));
        column_personnel_number.setCellValueFactory(new PropertyValueFactory<>(PERSONNEL_NUMBER.getEmployeeClassFieldsNames()));
        column_name.setCellValueFactory(new PropertyValueFactory<>(NAME.getEmployeeClassFieldsNames()));
        column_surname.setCellValueFactory(new PropertyValueFactory<>(SURNAME.getEmployeeClassFieldsNames()));

        listEmployee = employeeDaoImpl.findAllEmployees();
        table.setItems(listEmployee);
        choiceBoxItemNames.forEach((itemName) -> choice_box.getItems().add(itemName));
    }

    /**
     * <code>onFindButtonClick</code> method executes the main application logic on clicking the Find button. It allows to search
     * for data about employees in the database and display the results depending on the selected element (field) in the
     * drop-down list for which the search is performed and the text field in which a user input text to search for.
     */
    @FXML
    protected void onFindButtonClick() {
        ObservableList<Employee> listEmployee = null;
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
            FieldNames fieldNames = getSwitch(selectedItem);
            switch (fieldNames) {
                case ID -> {
                    listEmployee.add(employeeDaoImpl.findEmployeeById(Integer.parseInt(searchKeyWord)));
                    putItems(listEmployee);
                }
                case PERSONNEL_NUMBER -> {
                    listEmployee.add(employeeDaoImpl.findEmployeeByPersonnelNumber(Integer.parseInt(searchKeyWord)));
                    putItems(listEmployee);
                }
                case NAME -> {
                    listEmployee = employeeDaoImpl.findEmployeesByName(searchKeyWord);
                    putItems(listEmployee);
                }
                case SURNAME -> {
                    listEmployee = employeeDaoImpl.findEmployeesBySurname(searchKeyWord);
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

    /**
     * @return
     */
    private ArrayList<String> initializeChoiceBoxItems() {
        ArrayList<String> choiceBoxItemNames = new ArrayList<>();
        choiceBoxItemNames.add(ID.getChoiceBoxItemNames());
        choiceBoxItemNames.add(PERSONNEL_NUMBER.getChoiceBoxItemNames());
        choiceBoxItemNames.add(NAME.getChoiceBoxItemNames());
        choiceBoxItemNames.add(SURNAME.getChoiceBoxItemNames());
        return choiceBoxItemNames;
    }

    /**
     * @param listEmployee
     */
    private void putItems(ObservableList<Employee> listEmployee) {
        if (!listEmployee.isEmpty()) {
            table.setItems(listEmployee);
        } else {
            showAlert(NO_RESULTS, Alert.AlertType.INFORMATION, INFORMATION);
        }
    }

    /**
     * @param alertMessage
     * @param alertType
     * @param title
     */
    private void showAlert(String alertMessage, Alert.AlertType alertType, String title) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    /**
     * @param selectedItem
     * @return
     */
    public FieldNames getSwitch(String selectedItem) {
        Optional<FieldNames> enumValueOptional = Arrays.stream(FieldNames.values())
                .filter(v -> v.getChoiceBoxItemNames().equalsIgnoreCase(selectedItem))
                .findFirst();
        if (!enumValueOptional.isPresent()) {
            throw new RuntimeException();
        }
        FieldNames fieldNames = enumValueOptional.get();
        return fieldNames;
    }
}