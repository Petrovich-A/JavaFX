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
            LOGGER.log(Level.FATAL, String.format("%s file does not exist, initialization failed.", PROPERTY_PATH));
        }
        readProperties(inputStream);
    }

    /**
     * @param inputStream
     */
    private static void readProperties(InputStream inputStream) {
        try {
            PROPERTIES.load(inputStream);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.FATAL, String.format("Properties file not found: %s. %s", e.getMessage(), e));
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, String.format("Reading database properties failed: %s. %s", e.getMessage(), e));
        }
        LOGGER.log(Level.INFO, "Reading property file successful.");
    }

    /**
     * @param propertyName
     * @return
     */
    public String receive(String propertyName) {
        return PROPERTIES.getProperty(propertyName);
    }
}
