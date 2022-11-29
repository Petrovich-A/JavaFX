package by.javafx.petrovich.demo.util;

import javafx.scene.control.Alert;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static by.javafx.petrovich.demo.controller.AlertMessages.FAILED_READING_PROPERTIES_FILE;
import static by.javafx.petrovich.demo.controller.AlertMessages.NO_PROPERTIES_FILE;
import static by.javafx.petrovich.demo.controller.AlertTitleNames.ERROR;

/**
 * @author Petrovich A.V.
 */
public class PropertyLoader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PROPERTY_PATH = "src/main/resources/properties/jdbc-sql-config.properties";
    private static final Properties PROPERTIES = new Properties();
    private static final HealtheCheckController healtheCheckController = new HealtheCheckController();

    static {
        Path path = Paths.get(PROPERTY_PATH);
        try {
            InputStream inputStream = new FileInputStream(path.toFile());
            readProperties(inputStream);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, "File does not exist, initialization failed.", PROPERTY_PATH);
            healtheCheckController.showAlert(NO_PROPERTIES_FILE, Alert.AlertType.ERROR, ERROR);
            throw new RuntimeException("File does not exist, initialization failed.", e);
        }
    }

    /**
     * Read data from file
     *
     * @param inputStream link to file as a String
     */
    private static void readProperties(InputStream inputStream) {
        try {
            PROPERTIES.load(inputStream);
            LOGGER.log(Level.INFO, "Reading properties file successfully.");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Reading properties file failed", e.getMessage());
            healtheCheckController.showAlert(FAILED_READING_PROPERTIES_FILE, Alert.AlertType.ERROR, ERROR);
            throw new RuntimeException("Reading properties file failed.", e);
        }
    }

    /**
     * @param propertyName name of key in properties file
     * @return received value from properties file
     */
    public String receive(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }
}
