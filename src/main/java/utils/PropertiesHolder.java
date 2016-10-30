package utils;

import logger.LoggerContainer;

import javax.naming.ConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by work on 30.10.2016.
 */
public class PropertiesHolder {


    public static final String PATH_FOR_PROPERTY_FILE = "src/main/resources/app.properties";

    public static String getProperty(String value) {

        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(PATH_FOR_PROPERTY_FILE);
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(value);
    }

}
