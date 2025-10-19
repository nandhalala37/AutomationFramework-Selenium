package driver;

import config.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * WebDriverFactory creates instances of WebDriver based on configuration.
 */
public class WebDriverFactory {

    /**
     * Creates and returns a new instance of WebDriver.
     *
     * @return WebDriver based on config.properties settings
     */
    public static WebDriver createInstance() {
        String browser = ConfigManager.getBrowser();
        boolean headless = ConfigManager.isHeadless();

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new"); // For Chrome 115+
                options.addArguments("--disable-gpu", "--window-size=1920,1080");
            }
            return new ChromeDriver(options);
        }

        throw new RuntimeException("Unsupported browser: " + browser);
    }
}
