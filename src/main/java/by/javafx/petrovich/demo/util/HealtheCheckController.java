package by.javafx.petrovich.demo.util;

import javafx.scene.control.Alert;

public class HealtheCheckController {
    /**
     * @param alertMessage String information contains a sense of the alert message, viewed on UI
     * @param alertType    Alert is a part of JavaFX, so it is a subclass of Dialog class. Alerts are some predefined dialogs that are used to show some information to the user.
     *                     Alerts are basically of specific alert types:
     *                     <ul>
     *                         <li> CONFIRMATION alert
     *                         <li> WARNING alert
     *                         <li> INFORMATION alert
     *                         <li> ERROR alert
     *                     </ul>
     * @param title        The title of the alert window
     */
    public void showAlert(String alertMessage, Alert.AlertType alertType, String title) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
