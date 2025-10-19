package reports;

import org.testng.*;
import com.aventstack.extentreports.Status;
import utils.ScreenshotUtils;

/**
 * TestListener integrates with TestNG to log test events
 * (pass/fail/skip) into ExtentReports.
 */
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        ExtentTestManager.startTest(name, description);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String screenshotPath = ScreenshotUtils.takeScreenshot(testName);

        ExtentTestManager.getTest()
                .log(Status.FAIL, "Test failed: " + result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
