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
    private String EmployeeClassFieldsNames;
    private String ChoiceBoxItemNames;
    private String DateBaseColumnNames;

    /**
     * @param employeeClassFieldsNames
     * @param choiceBoxItemNames
     * @param dateBaseColumnNames
     */
    FieldNames(String employeeClassFieldsNames, String choiceBoxItemNames, String dateBaseColumnNames) {
        EmployeeClassFieldsNames = employeeClassFieldsNames;
        ChoiceBoxItemNames = choiceBoxItemNames;
        DateBaseColumnNames = dateBaseColumnNames;
    }

    public String getEmployeeClassFieldsNames() {
        return EmployeeClassFieldsNames;
    }

    public String getChoiceBoxItemNames() {
        return ChoiceBoxItemNames;
    }

    public String getDateBaseColumnNames() {
        return DateBaseColumnNames;
    }
}
