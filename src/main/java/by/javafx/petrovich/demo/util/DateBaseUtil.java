package by.javafx.petrovich.demo.util;

import javafx.fxml.Initializable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class DateBaseUtil implements Initializable {
    private static final Logger LOGGER = LogManager.getLogger();
    private static Connection connection = null;
    private static final PropertyLoader PROPERTY_LOADER = new PropertyLoader();
    private static final String JDBC_DRIVER_NAME = PROPERTY_LOADER.get("JDBC_DRIVER_NAME");
    private static final String CONNECTION_URL = PROPERTY_LOADER.get("CONNECTION_URL");
    private static final String USER_NAME = PROPERTY_LOADER.get("USER_NAME");
    private static final String PASSWORD = PROPERTY_LOADER.get("PASSWORD");

    public Connection receiveConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Class.forName(JDBC_DRIVER_NAME);
                Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
                LOGGER.log(Level.INFO, "Connection established.");
                return connection;
            } catch (Exception e) {
                LOGGER.log(Level.ERROR, "Connection Failed!", e);
                e.printStackTrace();
            }
            return connection;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
