package by.javafx.petrovich.demo.controller;

public enum FieldNames {
    ID("idEmployee", "id", "id_employee"),
    PERSONNEL_NUMBER("personnelNumber", "personnel number", "personnel_number"),
    NAME("name", "name", "name"),
    SURNAME("surname", "surname", "surname");
    private String EmployeeClassFieldsNames;
    private String ChoiceBoxItemNames;
    private String DateBaseColumnNames;

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
