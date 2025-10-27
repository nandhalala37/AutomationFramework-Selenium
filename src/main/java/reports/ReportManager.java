package reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import config.ConfigReader;
import constants.ConfigPropertiesKey;
import constants.Constants;

/**
 * Utility class responsible for managing ExtentReports setup and report generation.
 * <p>
 * This class provides centralized control over report creation, storage, and access.
 * It dynamically generates timestamped report folders and creates per-suite or per-test
 * HTML reports using {@link ExtentSparkReporter}.
 * </p>
 *
 * <p><b>Usage:</b></p>
 * <pre>
 * // Before suite execution
 * ReportManager.createReportFolder();
 *
 * // Before each test (e.g., in @BeforeTest)
 * ExtentReports report = ReportManager.createInstance("RegressionTest");
 *
 * // During test
 * ExtentTest test = report.createTest("Login Test");
 * test.pass("Login successful");
 *
 * // After test
 * report.flush();
 * </pre>
 * 
 * @author 
 * @version 1.0
 */
public class ReportManager {

    /** Shared ExtentReports instance for the current thread/test */
    private static ExtentReports extent;

    /** Base folder path where reports are generated */
    private static String baseReportFolderPath;

    /** Thread-local storage for parallel-safe ExtentReports handling */
    private static ThreadLocal<ExtentReports> extentThread = new ThreadLocal<>();

    /**
     * Creates the base folder for storing reports for the current suite run.
     * <p>
     * The folder name includes a timestamp to ensure unique report directories
     * for each suite execution.
     * </p>
     * <p>
     * Example:
     * <pre>
     * reports/20251026_194522/
     * </pre>
     * </p>
     */
    public static void createReportFolder() {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        baseReportFolderPath = Constants._ReportsFolderPath + timestamp;
        new File(baseReportFolderPath).mkdirs();
    }

    /**
     * Initializes and returns a new {@link ExtentReports} instance for a given TestNG test.
     * <p>
     * A separate HTML report file is created under the base report folder for each test.
     * </p>
     *
     * @param testName The name of the test (as defined in testng.xml)
     * @return Configured {@link ExtentReports} instance
     */
    public static ExtentReports createInstance(String testName) {
        String reportPath = baseReportFolderPath + File.separator + testName + "_ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        sparkReporter.config().setDocumentTitle(ConfigReader.getProperty(ConfigPropertiesKey.REPORTTITLE) == null?
        		"Automation Report - " + testName:ConfigReader.getProperty(ConfigPropertiesKey.REPORTTITLE)+" - "+testName);
        sparkReporter.config().setReportName("Execution Report for " + testName);
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
//        extent.setSystemInfo("Tester", "Automation User");
        extent.setSystemInfo("Test Name", testName);

        extentThread.set(extent);
        return extent;
    }

    /**
     * Retrieves the {@link ExtentReports} instance associated with the current thread.
     * <p>
     * This allows thread-safe access during parallel test execution.
     * </p>
     *
     * @return The {@link ExtentReports} instance for the current thread
     */
    public static ExtentReports getExtent() {
        return extentThread.get();
    }

    /**
     * Returns the base folder path where reports for the current suite are stored.
     *
     * @return The absolute path of the base report directory
     */
    public static String getBaseReportFolderPath() {
        return baseReportFolderPath;
    }
}
