package by.javafx.petrovich.demo.controller;

/**
 * This class contains enum's elements that refer to the fields of the Employee entity. passed into constructor Strings
 * depend on the name, which correspond to the fields of the Employee entity. The different field names correspond
 * to the different names are used:
 * <ul>
 * <li> in the UI (JavaFX) as <code>EmployeeClassFieldsNames</code>
 * <li> in populating the dropdown list on the UI as <code>ChoiceBoxItemNames</code>
 * <li> in the database field names (MuSQL) as <code>DateBaseColumnNames</code>.
 * </ul>
 * The corresponding names return a <code>String</code> type applying a simple get method.
 *
 * @author Petrovich A.V.
 */
public enum FieldNames {
    ID("idEmployee", "id", "id_employee"),
    PERSONNEL_NUMBER("personnelNumber", "personnel number", "personnel_number"),
    NAME("name", "name", "name"),
    SURNAME("surname", "surname", "surname");
    private final String employeeClassFieldsNames;
    private final String choiceBoxItemNames;
    private final String dateBaseColumnNames;

    /**
     * @param employeeClassFieldsNames for the UI (JavaFX) as <code>EmployeeClassFieldsNames</code>
     * @param choiceBoxItemNames for populating the dropdown list on the UI as <code>ChoiceBoxItemNames</code>
     * @param dateBaseColumnNames for the database field names (MuSQL) as <code>DateBaseColumnNames</code>.
     */
    FieldNames(String employeeClassFieldsNames, String choiceBoxItemNames, String dateBaseColumnNames) {
        this.employeeClassFieldsNames = employeeClassFieldsNames;
        this.choiceBoxItemNames = choiceBoxItemNames;
        this.dateBaseColumnNames = dateBaseColumnNames;
    }

    public String getEmployeeClassFieldsNames() {
        return employeeClassFieldsNames;
    }

    public String getChoiceBoxItemNames() {
        return choiceBoxItemNames;
    }

    public String getDateBaseColumnNames() {
        return dateBaseColumnNames;
    }
}
