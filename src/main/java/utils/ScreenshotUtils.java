package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Utility class for capturing and encoding browser screenshots using Selenium WebDriver.
 * <p>
 * This class provides a single static method to capture screenshots, store them locally,
 * and return their Base64-encoded string representation for integration with reporting tools
 * such as Extent Reports.
 * </p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *   <li>Captures browser screenshots via {@link TakesScreenshot}</li>
 *   <li>Saves the image file under the <code>/screenshots</code> directory with timestamp</li>
 *   <li>Encodes the image file in Base64 format for report embedding</li>
 * </ul>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>
 * WebDriver driver = new ChromeDriver();
 * String base64Image = ScreenshotUtils.captureScreenshot(driver, "LoginPage");
 * Logger.passWithScreenshot("Login successful");
 * </pre>
 *
 * @author 
 * @version 1.0
 */
public class ScreenshotUtils {

    /**
     * Captures a screenshot from the given {@link WebDriver} instance, saves it to disk,
     * and returns its Base64-encoded representation.
     * <p>
     * The screenshot file is saved inside a <code>screenshots</code> folder within the project root.
     * The filename includes both the provided screenshot name and a timestamp to ensure uniqueness.
     * </p>
     *
     * @param driver         The active {@link WebDriver} instance used for capturing the screenshot
     * @param screenshotName A meaningful name for the screenshot (e.g., test step or page name)
     * @return Base64-encoded screenshot string, or {@code null} if the operation fails
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots"
                + File.separator + screenshotName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            FileUtils.copyFile(src, dest);

            byte[] imageBytes = Files.readAllBytes(dest.toPath());
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
