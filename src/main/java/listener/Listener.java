package listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.Logger;
import reports.ReportManager;
import reports.TestManager;

/**
 * TestNG Listener implementation for Extent Reports integration.
 * <p>
 * This listener manages the lifecycle of ExtentReports across the entire test suite and 
 * individual test methods. It automatically creates reports, initializes test nodes, 
 * and logs the test status (pass, fail, skip) in Extent Reports.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Create timestamped report folders at suite start</li>
 *   <li>Generate a separate Extent Report per &lt;test&gt; tag in TestNG XML</li>
 *   <li>Create and manage {@link ExtentTest} nodes for each test method</li>
 *   <li>Log test results (PASS / FAIL / SKIP) with corresponding details</li>
 *   <li>Flush the report after test completion</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * &lt;listeners&gt;
 *   &lt;listener class-name="listener.Listener"/&gt;
 * &lt;/listeners&gt;
 * </pre>
 * 
 * @author 
 * @version 1.0
 */
public class Listener implements ITestListener, ISuiteListener {

    /**
     * Invoked before the test suite starts.
     * <p>
     * This method creates the base timestamped report folder once per suite execution.
     * </p>
     *
     * @param suite The current test suite
     */
    @Override
    public void onStart(ISuite suite) {
        ReportManager.createReportFolder();
    }

    /**
     * Invoked before each TestNG &lt;test&gt; starts.
     * <p>
     * Initializes a new {@link com.aventstack.extentreports.ExtentReports} instance
     * for the current test context.
     * </p>
     *
     * @param context The current test context
     */
    @Override
    public void onStart(ITestContext context) {
        ReportManager.createInstance(context.getName());
    }

    /**
     * Invoked when an individual test method starts execution.
     * <p>
     * Creates a new {@link ExtentTest} node for the test and associates it with the current thread.
     * </p>
     *
     * @param result The result of the test method
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest node = ReportManager.getExtent().createTest(result.getMethod().getMethodName());
        TestManager.setTest(node);
        Logger.info("Test Started: " + result.getMethod().getMethodName());
    }

    /**
     * Invoked when a test method passes successfully.
     *
     * @param result The result of the test method
     */
    @Override
    public void onTestSuccess(ITestResult result) {
    	Logger.pass("Test Passed : "+result.getName());
    }

    /**
     * Invoked when a test method fails.
     * <p>
     * Logs the failure reason (exception message) to the Extent Report.
     * </p>
     *
     * @param result The result of the failed test method
     */
    @Override
    public void onTestFailure(ITestResult result) {
    	TestManager.getTest().log(Status.FAIL, "Test Failed: " + result.getName()+"\n Error : \n"+result.getThrowable());
    }

    /**
     * Invoked when a test method is skipped.
     * <p>
     * Logs the skip reason (if available) to the Extent Report.
     * </p>
     *
     * @param result The result of the skipped test method
     */
    @Override
    public void onTestSkipped(ITestResult result) {
    	TestManager.getTest().log(Status.SKIP, "Test Failed: " + result.getName()+"\n Error : \n"+result.getThrowable());
    }

    /**
     * Invoked after all test methods within the current TestNG &lt;test&gt; are finished.
     * <p>
     * Flushes the {@link com.aventstack.extentreports.ExtentReports} instance to ensure 
     * all results are written to disk.
     * </p>
     *
     * @param context The test context that has just finished execution
     */
    @Override
    public void onFinish(ITestContext context) {
        ReportManager.getExtent().flush();
    }

    // The following suite-level finish method can be added if needed in future
    // @Override
    // public void onFinish(ISuite suite) { }
}
