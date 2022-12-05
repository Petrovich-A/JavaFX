package by.javafx.petrovich.demo;

import by.javafx.petrovich.demo.util.HealtheCheckController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static by.javafx.petrovich.demo.controller.AlertMessages.CANT_LOAD_FILE;
import static by.javafx.petrovich.demo.controller.AlertMessages.CANT_LOAD_XML_FILE;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.ERROR;

/**
 * @author Petrovich A.V.
 */
public class EmployeeApplication extends Application {
    private final HealtheCheckController healtheCheckController = new HealtheCheckController();

    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws Exception in case when don't have access to files
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("employeeSort.fxml"));
        try {
            InputStream inputStream = Optional.ofNullable(EmployeeApplication.class.getResourceAsStream("/icons/bussiness-man.png"))
                    .orElseThrow(FileNotFoundException::new);
            Image image = new Image(inputStream);
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);
            stage.setTitle("Employee app");
            stage.getIcons().add(image);
            stage.show();
        } catch (FileNotFoundException e) {
            healtheCheckController.showAlert(CANT_LOAD_FILE, Alert.AlertType.ERROR, ERROR);
            throw new RuntimeException(e);
        } catch (IOException e) {
            healtheCheckController.showAlert(CANT_LOAD_XML_FILE, Alert.AlertType.ERROR, ERROR);
            throw new RuntimeException(e);
        }
    }

    /**
     * @param args In Java `args` contains the supplied command-line arguments as an array of String objects.
     */
    public static void main(String[] args) {
        launch();
    }

}