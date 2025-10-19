package base;

import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * BasePage is an abstract class that all page objects should extend.
 * It provides access to the WebDriver instance and initializes web elements.
 */
public abstract class BasePage {
    protected WebDriver driver;

    /**
     * Constructor initializes the WebDriver and PageFactory elements.
     */
    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
}
