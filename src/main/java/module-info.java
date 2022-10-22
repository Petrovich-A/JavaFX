module by.javafx.petrovich.demo {
    requires javafx.controls;
    requires javafx.fxml;


    exports by.javafx.petrovich.demo;
    opens by.javafx.petrovich.demo to javafx.fxml;
}