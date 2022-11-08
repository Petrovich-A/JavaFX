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
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_LINK = "jdbc:mysql://localhost:3306/employeesort";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "HzaArk_XnsS";
    public Connection receiveConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Class.forName(JDBC_DRIVER_NAME);
                Connection connection = DriverManager.getConnection(CONNECTION_LINK, USER_NAME, PASSWORD);
                LOGGER.log(Level.INFO, String.format("Connection established."));
                return connection;
            } catch (Exception e) {
                LOGGER.log(Level.ERROR, String.format("Connection Failed!"), e);
                e.printStackTrace();
            }
            return connection;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


}
