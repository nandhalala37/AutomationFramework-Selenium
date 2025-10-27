package base;

import org.testng.annotations.*;

import config.ConfigReader;
import constants.ConfigPropertiesKey;
import driver.DriverManager;
import utils.ExcelUtils;

/**
 * Base class for all TestNG test classes.
 * <p>
 * Provides setup and teardown methods for initializing the WebDriver instance,
 * launching the base URL, and managing common test utilities like Excel data handling.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Launches browser and navigates to the base URL before each test</li>
 *   <li>Closes Excel workbook and quits browser after each test</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * public class LoginTest extends BaseClass {
 *     @Test
 *     public void verifyLogin() {
 *         // Test logic using DriverManager.getDriver()
 *     }
 * }
 * </pre>
 *
 * @author 
 * @version 1.0
 */
@Listeners(listener.Listener.class)
public class BaseClass {

    /** Excel utility instance for reading test data. */
    protected ExcelUtils excel;

    /**
     * Sets up browser and navigates to the configured base URL before each test method.
     * <p>
     * Reads the base URL from {@code config.properties} using {@link ConfigReader}.
     * </p>
     */
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        // Initialize WebDriver and navigate to base URL
        String baseUrl = ConfigReader.getProperty(ConfigPropertiesKey.BASEURL);
        DriverManager.getDriver().get(baseUrl);

        // Initialize Excel utility if test data is used (provide valid file path and sheet name)
        // Example:
        // excel = new ExcelUtils(Constants._TestDataFolderPath + File.separator + "TestData.xlsx", "Sheet1");
    }

    /**
     * Tears down resources after each test method.
     * <p>
     * Closes the Excel workbook (if initialized) and quits the WebDriver instance.
     * </p>
     */
    @AfterMethod(alwaysRun = true)
    public void teardown() {
        if (excel != null) {
            excel.closeWorkbook();
        }
        DriverManager.quitDriver();
    }
}
