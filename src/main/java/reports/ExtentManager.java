package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ExtentManager initializes and configures the ExtentReports instance
 * with a Spark reporter and output file.
 */
public class ExtentManager {

    private static ExtentReports extent;

    /**
     * Creates and returns a singleton ExtentReports instance.
     *
     * @return ExtentReports
     */
    public static ExtentReports createInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportPath = "test-output/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("Automation Report");
            reporter.config().setReportName("Test Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Author", "Automation Team");
        }

        return extent;
    }
}
