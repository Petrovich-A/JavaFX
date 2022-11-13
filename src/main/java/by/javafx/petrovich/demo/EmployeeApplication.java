package by.javafx.petrovich.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Petrovich A.V.
 *
 */
public class EmployeeApplication extends Application {
    /**
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(EmployeeApplication.class.getResource("employeeSort.fxml"));
        Path path = Paths.get("src/main/resources/icons/bussiness-man.png");
        InputStream inputStream = new FileInputStream(path.toFile());
        Image image = new Image(inputStream);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setScene(scene);
        stage.setTitle("Employee app");
        stage.getIcons().add(image);
        stage.show();
    }

	/**
	 * @param args
	 */
    public static void main(String[] args) {
        launch();
    }

}