package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Manages WebDriver instances for different browsers in a thread-safe manner.
 * <p>
 * This class centralizes driver initialization, retrieval, and cleanup. It uses
 * a {@link ThreadLocal} to ensure each parallel test thread has its own isolated
 * WebDriver instance, which prevents cross-thread interference during execution.
 * </p>
 *
 * <p><b>Supported Browsers:</b></p>
 * <ul>
 *   <li>Chrome</li>
 *   <li>Firefox</li>
 *   <li>Edge</li>
 *   <li>Safari (macOS only)</li>
 * </ul>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>
 * WebDriver driver = DriverManager.initDriver("chrome");
 * driver.get("https://example.com");
 * DriverManager.quitDriver();
 * </pre>
 *
 * @author 
 * @version 1.0
 */
public class DriverManager {

    /** Thread-local variable to hold WebDriver instances for parallel execution. */
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initializes a new WebDriver instance based on the specified browser type.
     * <p>
     * If a driver is already initialized for the current thread, it will return
     * the existing instance instead of creating a new one.
     * </p>
     *
     * @param browser Browser name (e.g., "chrome", "firefox", "edge", "safari")
     * @return The initialized {@link WebDriver} instance for the current thread
     */
    public static WebDriver initDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "firefox":
                    driver.set(new FirefoxDriver(FirefoxDriverManager.getFirefoxOptions()));
                    break;

                case "edge":
                    driver.set(new EdgeDriver(EdgeDriverManager.getEdgeOptions()));
                    break;

                case "safari":
                    driver.set(new SafariDriver(SafariDriverManager.getSafariOptions()));
                    break;

                case "chrome":
                default:
                    driver.set(new ChromeDriver(ChromeDriverManager.getChromeOptions()));
                    break;
            }
        }
        return driver.get();
    }

    /**
     * Retrieves the {@link WebDriver} instance associated with the current thread.
     *
     * @return The current thread’s {@link WebDriver} instance, or {@code null} if not initialized
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits the current thread's WebDriver instance and removes it from memory.
     * <p>
     * This should typically be called in an {@code @AfterMethod} or {@code @AfterTest}
     * block to ensure proper cleanup and resource release.
     * </p>
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    /** Private constructor to prevent instantiation. */
    private DriverManager() {}
}
