package utils;

import driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

/**
 * Utility class that provides reusable WebDriver actions.
 * <p>
 * Encapsulates common WebElement, JavaScript, Alert, Window, Dropdown, Actions,
 * and multi-element operations to simplify test code and enhance maintainability.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li>Basic element actions: click, type, getText, getAttribute, isDisplayed</li>
 *   <li>Dropdown interactions: select by text, index, or value</li>
 *   <li>Advanced Actions: hover, drag-and-drop, JavaScript click/type</li>
 *   <li>Scrolling: scroll to element or by pixel</li>
 *   <li>Alert handling: accept, dismiss, get text, send keys</li>
 *   <li>Window and tab handling: switch by title, switch to parent</li>
 *   <li>Checkbox & radio selection/deselection</li>
 *   <li>Handling multiple elements: click first visible</li>
 * </ul>
 *
 * <p>All methods internally use the {@link DriverManager#getDriver()} instance.</p>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>{@code
 * // Click an element
 * DriverUtils.click(By.id("loginBtn"));
 *
 * // Type into a text field
 * DriverUtils.type(By.name("username"), "admin");
 *
 * // Select from dropdown
 * DriverUtils.selectByVisibleText(By.id("country"), "India");
 *
 * // Handle alerts
 * DriverUtils.acceptAlert();
 *
 * // Hover over an element
 * DriverUtils.hoverOver(By.cssSelector(".menu"));
 * }</pre>
 *
 * @author 
 * @version 1.0
 * 
 */
public class DriverUtils {

    /** Returns the current WebDriver instance from {@link DriverManager}. */
    private static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    /** Returns a new Actions instance for advanced interactions. */
    private static Actions getActions() {
        return new Actions(getDriver());
    }

    // ---------------------- BASIC ELEMENT ACTIONS ----------------------

    public static void click(WebElement element) {
        element.click();
    }

    public static void click(By locator) {
        getDriver().findElement(locator).click();
    }

    public static void type(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static void type(By locator, String text) {
        WebElement element = getDriver().findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebElement element) {
        return element.getText();
    }

    public static String getText(By locator) {
        return getDriver().findElement(locator).getText();
    }

    public static String getAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public static String getAttribute(By locator, String attribute) {
        return getDriver().findElement(locator).getAttribute(attribute);
    }

    public static boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static boolean isDisplayed(By locator) {
        return getDriver().findElement(locator).isDisplayed();
    }

    // ---------------------- DROPDOWN ACTIONS ----------------------

    public static void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    public static void selectByVisibleText(By locator, String text) {
        new Select(getDriver().findElement(locator)).selectByVisibleText(text);
    }

    public static void selectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    public static void selectByIndex(By locator, int index) {
        new Select(getDriver().findElement(locator)).selectByIndex(index);
    }

    public static void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    public static void selectByValue(By locator, String value) {
        new Select(getDriver().findElement(locator)).selectByValue(value);
    }

    // ---------------------- ACTIONS & JAVASCRIPT ----------------------

    public static void hoverOver(WebElement element) {
        getActions().moveToElement(element).perform();
    }

    public static void hoverOver(By locator) {
        getActions().moveToElement(getDriver().findElement(locator)).perform();
    }

    public static void dragAndDrop(WebElement source, WebElement target) {
        getActions().dragAndDrop(source, target).perform();
    }

    public static void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = getDriver().findElement(sourceLocator);
        WebElement target = getDriver().findElement(targetLocator);
        getActions().dragAndDrop(source, target).perform();
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToElement(By locator) {
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView(true);", getDriver().findElement(locator));
    }

    public static void scrollBy(int x, int y) {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    public static void jsClick(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void jsClick(By locator) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", getDriver().findElement(locator));
    }

    public static void jsType(WebElement element, String text) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value='" + text + "';", element);
    }

    public static void jsType(By locator, String text) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].value='" + text + "';", getDriver().findElement(locator));
    }

    // ---------------------- ALERTS ----------------------

    public static void acceptAlert() {
        getDriver().switchTo().alert().accept();
    }

    public static void dismissAlert() {
        getDriver().switchTo().alert().dismiss();
    }

    public static String getAlertText() {
        return getDriver().switchTo().alert().getText();
    }

    public static void sendKeysToAlert(String text) {
        getDriver().switchTo().alert().sendKeys(text);
    }

    // ---------------------- WINDOW & TAB HANDLING ----------------------

    public static void switchToWindow(String windowTitle) {
        Set<String> windows = getDriver().getWindowHandles();
        for (String win : windows) {
            getDriver().switchTo().window(win);
            if (getDriver().getTitle().equalsIgnoreCase(windowTitle)) {
                break;
            }
        }
    }

    public static void switchToParentWindow() {
        Set<String> windows = getDriver().getWindowHandles();
        getDriver().switchTo().window(windows.iterator().next());
    }

    // ---------------------- CHECKBOX & RADIO ----------------------

    public static void selectCheckbox(WebElement element) {
        if (!element.isSelected()) element.click();
    }

    public static void selectCheckbox(By locator) {
        WebElement element = getDriver().findElement(locator);
        if (!element.isSelected()) element.click();
    }

    public static void deselectCheckbox(WebElement element) {
        if (element.isSelected()) element.click();
    }

    public static void deselectCheckbox(By locator) {
        WebElement element = getDriver().findElement(locator);
        if (element.isSelected()) element.click();
    }

    // ---------------------- MULTIPLE ELEMENTS ----------------------

    public static void clickFirstVisible(List<WebElement> elements) {
        for (WebElement e : elements) {
            if (e.isDisplayed()) {
                click(e);
                break;
            }
        }
    }

    public static void clickFirstVisible(By locator) {
        List<WebElement> elements = getDriver().findElements(locator);
        for (WebElement e : elements) {
            if (e.isDisplayed()) {
                click(e);
                break;
            }
        }
    }
}
