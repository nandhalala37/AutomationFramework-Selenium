package reports;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import driver.DriverManager;
import utils.ScreenshotUtils;

/**
 * Utility class for logging messages and screenshots into Extent Reports.
 * <p>
 * This class provides centralized static methods to log messages of different statuses
 * (INFO, PASS, FAIL, WARNING, SKIP) to the active {@link com.aventstack.extentreports.ExtentTest}
 * instance managed by {@link TestManager}.
 * </p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *   <li>Standard logging for all test statuses</li>
 *   <li>Logging messages with optional screenshots</li>
 *   <li>Automatic screenshot capture using {@link ScreenshotUtils}</li>
 *   <li>Thread-safe execution with {@link DriverManager}</li>
 * </ul>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * Logger.info("Navigated to home page");
 * Logger.passWithScreenshot("Login successful");
 * Logger.fail("Login failed due to invalid credentials");
 * </pre>
 *
 * @see TestManager
 * @see ScreenshotUtils
 * @see DriverManager
 * 
 * @author 
 * @version 1.0
 */
public class Logger {

    /** Logs an informational message. */
    public static void info(String message) {
        TestManager.getTest().log(Status.INFO, message);
    }

    /** Logs a PASS status message. */
    public static void pass(String message) {
        TestManager.getTest().log(Status.PASS, message);
    }

    /** Logs a FAIL status message. */
    public static void fail(String message) {
        TestManager.getTest().log(Status.FAIL, message);
    }

    /** Logs a WARNING status message. */
    public static void warning(String message) {
        TestManager.getTest().log(Status.WARNING, message);
    }

    /** Logs a SKIP status message. */
    public static void skip(String message) {
        TestManager.getTest().log(Status.SKIP, message);
    }

    /** Logs an INFO message with an attached screenshot. */
    public static void infoWithScreenshot(String message) {
        attachScreenshot(Status.INFO, message);
    }

    /** Logs a PASS message with an attached screenshot. */
    public static void passWithScreenshot(String message) {
        attachScreenshot(Status.PASS, message);
    }

    /** Logs a FAIL message with an attached screenshot. */
    public static void failWithScreenshot(String message) {
        attachScreenshot(Status.FAIL, message);
    }

    /** Logs a WARNING message with an attached screenshot. */
    public static void warningwithScreenshot(String message) {
        attachScreenshot(Status.WARNING, message);
    }

    /** Logs a SKIP message with an attached screenshot. */
    public static void skipwithScreenshot(String message) {
        attachScreenshot(Status.SKIP, message);
    }

    /**
     * Captures a screenshot from the active {@link WebDriver} instance and attaches it
     * to the Extent Report under the specified {@link Status}.
     * <p>
     * This method automatically handles null drivers and exceptions gracefully,
     * logging warnings instead of throwing errors.
     * </p>
     *
     * @param status  The log status to associate with the screenshot
     * @param message The log message to be displayed in the report
     */
    private static void attachScreenshot(Status status, String message) {
        WebDriver driver = DriverManager.getDriver();
        try {
            if (driver != null) {
                String path = ScreenshotUtils.captureScreenshot(
                        driver, message.replaceAll("[^a-zA-Z0-9]", "_"));
                TestManager.getTest().log(
                        status, message,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
            } else {
                TestManager.getTest().log(status, message + " (screenshot skipped - driver not set)");
            }
        } catch (Exception e) {
            TestManager.getTest().log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
        }
    }
}
