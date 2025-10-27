package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.ConfigReader;
import constants.ConfigPropertiesKey;
import driver.DriverManager;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Utility class providing reusable wait methods for Selenium WebDriver.
 * <p>
 * This class centralizes all waiting mechanisms, ensuring consistent and configurable
 * synchronization across the framework. It leverages both {@link WebDriverWait}
 * and {@link FluentWait} for handling dynamic web elements efficiently.
 * </p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *   <li>Explicit waits for visibility, clickability, and presence</li>
 *   <li>Fluent waits with configurable polling</li>
 *   <li>Graceful handling of stale and missing elements</li>
 *   <li>Custom pause method for short manual waits</li>
 * </ul>
 *
 * <p><b>Configuration:</b></p>
 * The default timeout value is fetched from <code>config.properties</code>:
 * <pre>
 * implicitWait=10
 * </pre>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>
 * WebElement loginButton = WaitUtils.waitForElementToBeClickable(driver.findElement(By.id("loginBtn")));
 * loginButton.click();
 * </pre>
 *
 * @author 
 * @version 1.0
 */
public class WaitUtils {

    /** Default timeout duration in seconds, loaded from config.properties */
    private static final int DEFAULT_TIMEOUT = Integer.parseInt(
            ConfigReader.getProperty(ConfigPropertiesKey.WAITTIMEOUT));

    /** Polling interval in milliseconds for FluentWait */
    private static final int POLLING_INTERVAL = 500;

    /** Private constructor to prevent instantiation. */
    private WaitUtils() {}

    /**
     * Waits until the given element becomes visible on the page.
     *
     * @param element The {@link WebElement} to wait for
     * @return The visible {@link WebElement}
     */
    public static WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the given element becomes clickable.
     *
     * @param element The {@link WebElement} to wait for
     * @return The clickable {@link WebElement}
     */
    public static WebElement waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until an element located by the given locator becomes invisible.
     *
     * @param locator The {@link By} locator for the element
     * @return {@code true} if the element is invisible, {@code false} otherwise
     */
    public static boolean waitForInvisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits until an element located by the given locator is present in the DOM.
     *
     * @param locator The {@link By} locator for the element
     * @return The located {@link WebElement}
     */
    public static WebElement waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Performs a FluentWait for the specified locator, retrying at regular intervals
     * until the element is found or the timeout expires.
     * <p>
     * Ignores {@link NoSuchElementException} and {@link StaleElementReferenceException}
     * during polling.
     * </p>
     *
     * @param locator The {@link By} locator of the element
     * @return The found {@link WebElement}
     */
    public static WebElement fluentWait(final By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    /**
     * Pauses execution for a specified number of milliseconds.
     * <p>
     * This method is preferred over directly using {@link Thread#sleep(long)} to maintain
     * consistency and improve readability.
     * </p>
     *
     * @param millis Duration to pause in milliseconds
     */
    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}
