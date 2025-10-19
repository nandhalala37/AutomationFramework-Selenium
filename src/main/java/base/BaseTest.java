package base;

import org.testng.annotations.*;
import driver.DriverManager;
import reports.ExtentManager;
import reports.ExtentTestManager;
import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;

/**
 * BaseTest is the foundation for all TestNG test classes.
 * It manages the WebDriver lifecycle, ExtentReports setup, and per-method test logs.
 */
@Listeners(reports.TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    private ExtentReports extent;

    /**
     * Initializes the ExtentReports instance before the test suite runs.
     */
    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.createInstance();
    }

    /**
     * Initializes the WebDriver and ExtentTest logger before each test method.
     *
     * @param method Java reflection Method object representing the current test method
     */
    @BeforeMethod
    public void setupDriver(Method method) {
        driver = DriverManager.initializeDriver();
        ExtentTestManager.startTest(method.getName(), method.getAnnotation(Test.class).description());
    }

    /**
     * Quits the WebDriver after each test method.
     */
    @AfterMethod
    public void tearDownDriver() {
        DriverManager.quitDriver();
    }

    /**
     * Flushes the ExtentReports instance after the entire suite is executed.
     */
    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}
