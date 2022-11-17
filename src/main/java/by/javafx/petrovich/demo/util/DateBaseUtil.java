package by.javafx.petrovich.demo.util;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.Initializable;

/**
 * Receiving a connection with database using data stored in property file.
 *
 * @author Petrovich A.V.
 */
public class DateBaseUtil implements Initializable {
    private static Connection connection;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final PropertyLoader PROPERTY_LOADER = new PropertyLoader();
    private static final String JDBC_DRIVER_NAME = PROPERTY_LOADER.receive("JDBC_DRIVER_NAME");
    private static final String CONNECTION_URL = PROPERTY_LOADER.receive("CONNECTION_URL");
    private static final String USER_NAME = PROPERTY_LOADER.receive("USER_NAME");
    private static final String PASSWORD = PROPERTY_LOADER.receive("PASSWORD");

    public Connection receiveConnection() throws SQLException {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER_NAME);
                Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
                LOGGER.log(Level.INFO, "Connection established.");
                return connection;
            } catch (Exception e) {
                LOGGER.log(Level.ERROR, "Connection Failed!", e);
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * @param url            The location used to resolve relative paths for the root object, or
     *                       {@code null} if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or {@code null} if
     *                       the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
