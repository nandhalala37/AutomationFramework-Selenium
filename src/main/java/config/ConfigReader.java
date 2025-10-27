package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import constants.ConfigPropertiesKey;
import constants.Constants;

/**
 * Utility class to read configuration properties from a properties file.
 * <p>
 * This class loads the properties file specified in {@link Constants#_ConfigPropertiesFilePath}
 * and provides a method to retrieve property values using {@link ConfigPropertiesKey}.
 * The class is designed as a singleton-like utility with a private constructor and static methods.
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class ConfigReader {

    /** Holds the loaded configuration properties */
    private static Properties properties = new Properties();
    
    /**
     * Private constructor to prevent instantiation.
     * <p>
     * This class is intended to be used statically.
     * </p>
     */
    private ConfigReader() {}
    
    // Static block to automatically load properties when the class is first loaded
    static {
        loadConfigFile();
    }
    
    /**
     * Loads the configuration properties file into memory.
     * <p>
     * The properties file path is defined in {@link Constants#_ConfigPropertiesFilePath}.
     * If the file is not found or cannot be loaded, the stack trace is printed.
     * </p>
     */
    private static void loadConfigFile() {
        try {
            properties.load(new FileInputStream(Constants._ConfigPropertiesFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves the value of a configuration property based on the provided key.
     *
     * @param key The {@link ConfigPropertiesKey} enum representing the desired property key.
     * @return The value of the property as a {@link String}, or {@code null} if the key is not found.
     */
    public static String getProperty(ConfigPropertiesKey key) {
    	if(Objects.isNull(key.getKey()) || key.getKey()=="")
    		throw new IllegalArgumentException("The Property Key"+key.getKey() +"is invalid. Please enter valid key. Check "
    				+ "Properties file : "+Constants._ConfigPropertiesFilePath);
        return properties.getProperty(key.getKey());
    }
    
}
