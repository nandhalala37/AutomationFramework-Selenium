package reports;

import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

/**
 * ExtentTestManager manages ExtentTest instances on a per-thread basis.
 */
public class ExtentTestManager {

    private static final Map<Long, ExtentTest> extentTestMap = new HashMap<>();

    /**
     * Gets the ExtentTest instance for the current thread.
     *
     * @return ExtentTest for the current thread
     */
    public static ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    /**
     * Starts a new test log and binds it to the current thread.
     *
     * @param testName Name of the test
     * @param description Optional test description
     */
    public static void startTest(String testName, String description) {
        ExtentTest test = ExtentManager.createInstance().createTest(testName, description);
        extentTestMap.put(Thread.currentThread().getId(), test);
    }
}
