package driver;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager manages WebDriver instances in a thread-safe manner
 * for parallel execution support.
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    /**
     * Returns the current thread's WebDriver instance.
     *
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return driverThread.get();
    }

    /**
     * Initializes and sets the WebDriver for the current thread.
     *
     * @return WebDriver
     */
    public static WebDriver initializeDriver() {
        WebDriver driver = WebDriverFactory.createInstance();
        driverThread.set(driver);
        return driver;
    }

    /**
     * Quits and removes the WebDriver for the current thread.
     */
    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
