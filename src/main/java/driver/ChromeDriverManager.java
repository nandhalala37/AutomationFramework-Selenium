package driver;

import org.openqa.selenium.chrome.ChromeOptions;

import config.ConfigReader;
import constants.ConfigPropertiesKey;

/**
 * Utility class to manage Chrome browser options for Selenium WebDriver.
 * <p>
 * Provides a centralized method to configure and return {@link ChromeOptions} 
 * based on framework configuration, including headless mode, maximized window, 
 * and disabling notifications and info bars.
 * </p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>
 * ChromeOptions options = ChromeDriverManager.getChromeOptions();
 * WebDriver driver = new ChromeDriver(options);
 * </pre>
 * 
 * @author 
 * @version 1.0
 */
public class ChromeDriverManager {
    
    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * All methods in this class are static and should be accessed directly.
     * </p>
     */
    private ChromeDriverManager() {}

    /** Value of the "headless" property from config.properties */
    private static String headless = ConfigReader.getProperty(ConfigPropertiesKey.HEADLESS);

    /**
     * Creates and returns a {@link ChromeOptions} object with default configurations.
     * <p>
     * The following options are applied:
     * <ul>
     *   <li>Start browser maximized</li>
     *   <li>Disable browser notifications</li>
     *   <li>Disable Chrome info bars ("Chrome is being controlled by automated test software")</li>
     *   <li>Enable headless mode if {@code headless=true} in {@code config.properties}</li>
     * </ul>
     * </p>
     *
     * @return Configured {@link ChromeOptions} instance
     */
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); 
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars"); // Removes "Chrome is being controlled by automated test software"
        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless");
        }
        return options;
    }
}
