package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigManager loads and provides access to configuration
 * values from a `.properties` file located in the resources directory.
 */
public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    /**
     * Loads the properties file into memory.
     */
    private static void loadProperties() {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    /**
     * Gets the base URL for UI tests.
     * @return base URL as String
     */
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    /**
     * Gets the base URL for API tests.
     * @return API base URL as String
     */
    public static String getApiBaseUrl() {
        return properties.getProperty("apiBaseUrl");
    }

    /**
     * Gets the browser name to use for WebDriver.
     * @return browser name as String
     */
    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    /**
     * Checks if headless mode is enabled.
     * @return true if headless, false otherwise
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    /**
     * Gets the default timeout duration in seconds.
     * @return timeout in int
     */
    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}
