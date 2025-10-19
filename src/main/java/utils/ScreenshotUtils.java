package utils;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotUtils provides utility to take screenshots, especially on test failures.
 */
public class ScreenshotUtils {

    private static final String SCREENSHOT_FOLDER = "test-output/screenshots/";

    /**
     * Captures a screenshot and stores it in the screenshots folder with a timestamp.
     *
     * @param testName Name of the test for filename
     * @return Path to the saved screenshot
     */
    public static String takeScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = SCREENSHOT_FOLDER + testName + "_" + timestamp + ".png";
        File destFile = new File(filePath);

        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + filePath, e);
        }

        return filePath;
    }
}
