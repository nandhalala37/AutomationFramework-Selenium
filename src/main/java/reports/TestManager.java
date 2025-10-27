package reports;

import com.aventstack.extentreports.ExtentTest;

/**
 * Manages thread-safe instances of {@link ExtentTest} for parallel test execution.
 * <p>
 * This utility class ensures that each test running in a separate thread
 * (e.g., under TestNG parallel mode or Selenium Grid) maintains its own
 * {@link ExtentTest} instance. This prevents cross-thread logging issues
 * and ensures accurate report generation per test.
 * </p>
 *
 * <p><b>Usage Example:</b></p>
 * <pre>
 * ExtentTest test = ReportManager.getExtent().createTest("LoginTest");
 * TestManager.setTest(test);
 * TestManager.getTest().info("Navigated to login page");
 * </pre>
 *
 * @author 
 * @version 1.0
 */
public class TestManager {
    
    /** Thread-local variable to maintain a unique ExtentTest instance per thread. */
    private static final ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();

    /**
     * Associates an {@link ExtentTest} instance with the current thread.
     * <p>
     * Typically called when a new test starts (e.g., in {@code onTestStart()}).
     * </p>
     *
     * @param test The {@link ExtentTest} instance to associate with the current thread.
     */
    public static void setTest(ExtentTest test) {
        extentTestThread.set(test);
    }

    /**
     * Retrieves the {@link ExtentTest} instance associated with the current thread.
     * <p>
     * Used by loggers or reporting utilities to record test steps
     * for the currently executing test.
     * </p>
     *
     * @return The current thread’s {@link ExtentTest} instance, or {@code null} if not set.
     */
    public static ExtentTest getTest() {
        return extentTestThread.get();
    }

    /**
     * Logs a custom informational message to the current test’s Extent Report.
     * <p>
     * A convenience method to log steps directly without accessing the
     * {@link ExtentTest} object explicitly.
     * </p>
     *
     * @param message The message to log under the INFO level.
     * @throws NullPointerException if no test instance is set for the thread.
     */
    public static void log(String message) {
        getTest().info(message);
    }

    /**
     * Removes the {@link ExtentTest} instance associated with the current thread.
     * <p>
     * Should be called at the end of each test to free up memory and
     * prevent thread leakage in long-running executions.
     * </p>
     */
    public static void unload() {
        extentTestThread.remove();
    }
}
