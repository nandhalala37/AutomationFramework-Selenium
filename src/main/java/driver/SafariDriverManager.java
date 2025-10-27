package driver;

import org.openqa.selenium.safari.SafariOptions;

import config.ConfigReader;
import constants.ConfigPropertiesKey;

/**
 * Utility class to manage Safari browser options for Selenium WebDriver.
 * <p>
 * Provides a centralized method to configure and return {@link SafariOptions}
 * based on framework configuration, including optional headless mode (available in Safari Technology Preview)
 * and enabling the automatic driver connection feature.
 * </p>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>
 * SafariOptions options = SafariDriverManager.getSafariOptions();
 * WebDriver driver = new SafariDriver(options);
 * </pre>
 * 
 * @author 
 * @version 1.0
 */
public class SafariDriverManager {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * <p>
     * All methods are static and should be accessed directly.
     * </p>
     */
    private SafariDriverManager() {}

    /** Value of the "headless" property from config.properties */
    private static String headless = ConfigReader.getProperty(ConfigPropertiesKey.HEADLESS);

    /**
     * Creates and returns a {@link SafariOptions} object with default configurations.
     * <p>
     * The following options are applied:
     * <ul>
     *   <li>Enables automatic driver connection</li>
     *   <li>Enables headless mode if {@code headless=true} (only in Safari Technology Preview)</li>
     * </ul>
     * </p>
     *
     * @return Configured {@link SafariOptions} instance
     */
    public static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();

        // Enables automatic connection between SafariDriver and Safari browser
        options.setAutomaticInspection(false);
        options.setAutomaticProfiling(false);

        // Headless mode is supported only in Safari Technology Preview (macOS)
        if (Boolean.parseBoolean(headless)) {
            options.setCapability("safari.options.headless", true);
        }

        return options;
    }
}
