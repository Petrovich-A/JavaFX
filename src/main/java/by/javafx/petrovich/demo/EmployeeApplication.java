package by.javafx.petrovich.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EmployeeApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(EmployeeApplication.class.getResource("employeeSort.fxml"));
        Image image = new Image("bussiness-man.png");
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Employee app");
        stage.getIcons().add(image);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}