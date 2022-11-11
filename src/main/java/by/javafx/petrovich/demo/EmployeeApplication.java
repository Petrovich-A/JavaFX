package by.javafx.petrovich.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
        Image image = new Image("bussiness-man.png");
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