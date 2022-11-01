module by.javafx.petrovich.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports by.javafx.petrovich.demo;
    exports by.javafx.petrovich.demo.model;
    exports by.javafx.petrovich.demo.controller;

    opens by.javafx.petrovich.demo to javafx.fxml;
    opens by.javafx.petrovich.demo.model to javafx.fxml;
    opens by.javafx.petrovich.demo.controller to javafx.fxml;
}