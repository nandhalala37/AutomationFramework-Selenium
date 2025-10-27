package driver;

import org.openqa.selenium.edge.EdgeOptions;

import config.ConfigReader;
import constants.ConfigPropertiesKey;

/**
 * Utility class to manage Microsoft Edge browser options for Selenium WebDriver.
 * <p>
 * Provides a centralized method to configure and return {@link EdgeOptions} 
 * based on framework configuration, including headless mode, maximized window, 
 * and disabling notifications and info bars.
 * </p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>
 * EdgeOptions options = EdgeDriverManager.getEdgeOptions();
 * WebDriver driver = new EdgeDriver(options);
 * </pre>
 * 
 * @author 
 * @version 1.0
 */
public class EdgeDriverManager {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * All methods are static and should be accessed directly.
     * </p>
     */
    private EdgeDriverManager() {}
    
    /** Value of the "headless" property from config.properties */
    private static String headless = ConfigReader.getProperty(ConfigPropertiesKey.HEADLESS);
    
    /**
     * Creates and returns an {@link EdgeOptions} object with default configurations.
     * <p>
     * The following options are applied:
     * <ul>
     *   <li>Start browser maximized</li>
     *   <li>Disable browser notifications</li>
     *   <li>Disable Edge info bars ("Edge is being controlled by automated test software")</li>
     *   <li>Enable headless mode if {@code headless=true} in {@code config.properties}</li>
     * </ul>
     * </p>
     *
     * @return Configured {@link EdgeOptions} instance
     */
    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars"); // Removes "Edge is being controlled by automated test software"
        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless");
        }
        return options;
    }
}
