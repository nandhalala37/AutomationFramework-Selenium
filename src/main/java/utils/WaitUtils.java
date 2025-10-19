package utils;

import config.ConfigManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/**
 * WaitUtils provides explicit and fluent wait helpers.
 */
public class WaitUtils {

    private static final int TIMEOUT = ConfigManager.getTimeout();

    /**
     * Waits for an element to be visible on the page.
     *
     * @param driver  WebDriver instance
     * @param locator By locator of the element
     * @return WebElement when visible
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable.
     *
     * @param driver  WebDriver instance
     * @param locator By locator of the element
     * @return WebElement when clickable
     */
    public static WebElement waitForClickability(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits for a page title to contain a specific string.
     *
     * @param driver WebDriver instance
     * @param title  Expected title fragment
     */
    public static void waitForTitleContains(WebDriver driver, String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        wait.until(ExpectedConditions.titleContains(title));
    }
}
