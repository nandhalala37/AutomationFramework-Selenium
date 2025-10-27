package driver;

import org.openqa.selenium.firefox.FirefoxOptions;

import config.ConfigReader;
import constants.ConfigPropertiesKey;

/**
 * Utility class to manage Firefox browser options for Selenium WebDriver.
 * <p>
 * Provides a centralized method to configure and return {@link FirefoxOptions}
 * based on framework configuration, including headless mode and window size.
 * </p>
 * 
 * <p><b>Example Usage:</b></p>
 * <pre>
 * FirefoxOptions options = FirefoxDriverManager.getFirefoxOptions();
 * WebDriver driver = new FirefoxDriver(options);
 * </pre>
 * 
 * @author 
 * @version 1.0
 */
public class FirefoxDriverManager {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * All methods are static and should be accessed directly.
     * </p>
     */
    private FirefoxDriverManager() {}

    /** Value of the "headless" property from config.properties */
    private static String headless = ConfigReader.getProperty(ConfigPropertiesKey.HEADLESS);

    /**
     * Creates and returns a {@link FirefoxOptions} object with default configurations.
     * <p>
     * The following options are applied:
     * <ul>
     *   <li>Disables browser notifications</li>
     *   <li>Sets a large window size (simulating maximized mode)</li>
     *   <li>Enables headless mode if {@code headless=true} in {@code config.properties}</li>
     * </ul>
     * </p>
     *
     * @return Configured {@link FirefoxOptions} instance
     */
    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("dom.webnotifications.enabled", false); // Disable notifications
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless");
        }
        return options;
    }
}
