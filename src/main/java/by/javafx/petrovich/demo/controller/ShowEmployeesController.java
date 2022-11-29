package by.javafx.petrovich.demo.controller;

import by.javafx.petrovich.demo.dao.impl.EmployeeDaoImpl;
import by.javafx.petrovich.demo.model.Employee;
import by.javafx.petrovich.demo.util.HealtheCheckController;
import javafx.collections.FXCollections;
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
    private final EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
    private final HealtheCheckController healtheCheckController = new HealtheCheckController();

    /**
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> choiceBoxItemNames = populateChoiceBoxItems();
        column_id.setCellValueFactory(new PropertyValueFactory<>(ID.getEmployeeClassFieldsNames()));
        column_personnel_number.setCellValueFactory(new PropertyValueFactory<>(PERSONNEL_NUMBER.getEmployeeClassFieldsNames()));
        column_name.setCellValueFactory(new PropertyValueFactory<>(NAME.getEmployeeClassFieldsNames()));
        column_surname.setCellValueFactory(new PropertyValueFactory<>(SURNAME.getEmployeeClassFieldsNames()));
        ObservableList<Employee> listEmployee = employeeDaoImpl.findAllEmployees();
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
        ObservableList<Employee> listEmployee = FXCollections.observableArrayList();
        String selectedItem;
        String searchKeyWord;
        try {
            selectedItem = choice_box.getSelectionModel().getSelectedItem();
            searchKeyWord = text_field.getText();
            if (selectedItem == null) {
                healtheCheckController.showAlert(NO_CHOICE_ITEM, Alert.AlertType.WARNING, WARNING);
                return;
            }
            if (searchKeyWord.isEmpty()) {
                healtheCheckController.showAlert(NO_INPUT_VALUE, Alert.AlertType.WARNING, WARNING);
                return;
            }
            FieldNames fieldNames = defineEnumElement(selectedItem);
            switch (fieldNames) {
                case ID -> {
                    listEmployee.add(employeeDaoImpl.findEmployeeById(Integer.parseInt(searchKeyWord)));
                    populateTable(listEmployee);
                }
                case PERSONNEL_NUMBER -> {
                    listEmployee.add(employeeDaoImpl.findEmployeeByPersonnelNumber(Integer.parseInt(searchKeyWord)));
                    populateTable(listEmployee);
                }
                case NAME -> {
                    listEmployee = employeeDaoImpl.findEmployeesByName(searchKeyWord);
                    populateTable(listEmployee);
                }
                case SURNAME -> {
                    listEmployee = employeeDaoImpl.findEmployeesBySurname(searchKeyWord);
                    populateTable(listEmployee);
                }
                default -> {
                }
            }
        } catch (NumberFormatException e) {
            healtheCheckController.showAlert(INPUT_NUMBER, Alert.AlertType.WARNING, ERROR);
        } catch (Exception e) {
            healtheCheckController.showAlert(CHOICE_AND_FILL, Alert.AlertType.ERROR, INFORMATION);
        }
    }

    /**
     * Fill the ChoiceBox on UI with items for selecting by them.
     *
     * @return choiceBoxItemNames <code>ArrayList<String></code> list of items
     */
    private ArrayList<String> populateChoiceBoxItems() {
        ArrayList<String> choiceBoxItemNames = new ArrayList<>();
        choiceBoxItemNames.add(ID.getChoiceBoxItemNames());
        choiceBoxItemNames.add(PERSONNEL_NUMBER.getChoiceBoxItemNames());
        choiceBoxItemNames.add(NAME.getChoiceBoxItemNames());
        choiceBoxItemNames.add(SURNAME.getChoiceBoxItemNames());
        return choiceBoxItemNames;
    }

    /**
     * @param listEmployee <code>ObservableList<Employee></code>
     */
    private void populateTable(ObservableList<Employee> listEmployee) {
        if (!listEmployee.isEmpty()) {
            table.setItems(listEmployee);
        } else {
            healtheCheckController.showAlert(NO_RESULTS, Alert.AlertType.INFORMATION, INFORMATION);
        }
    }

    /**
     * @param selectedItem The item (id, personnel number, name, surname) selected in dropdown list in ChoiceBox on UI
     * @return <code>FieldNames fieldNames</code> The name of the selected item
     */
    private FieldNames defineEnumElement(String selectedItem) {
        Optional<FieldNames> enumValueOptional = Arrays.stream(FieldNames.values())
                .filter(v -> v.getChoiceBoxItemNames().equalsIgnoreCase(selectedItem))
                .findFirst();
        if (enumValueOptional.isEmpty()) {
            throw new RuntimeException();
        }
        return enumValueOptional.get();
    }
}