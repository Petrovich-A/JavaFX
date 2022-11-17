package by.javafx.petrovich.demo.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Petrovich A.V.
 */
public class PropertyLoader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PROPERTY_PATH = "jdbc-sql-config.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(PROPERTY_PATH);
        if (inputStream == null) {
            LOGGER.log(Level.FATAL, "File does not exist, initialization failed.", PROPERTY_PATH);
        }
        readProperties(inputStream);
    }

    /**
     * Read data from file
     *
     * @param inputStream link to file as a String
     */
    private static void readProperties(InputStream inputStream) {
        try {
            PROPERTIES.load(inputStream);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, "Properties file not found", e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Reading database properties failed", e.getMessage());
        }
        LOGGER.log(Level.INFO, "Reading property file successful.");
    }

    /**
     * @param propertyName name of key in Properties file
     * @return receive value
     */
    public String receive(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }
}
