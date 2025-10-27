/**
 * Provides all Page Object Model (POM) classes for the automation framework.
 * <p>
 * Each class in this package represents a specific page, component, or module
 * of the application under test. The goal of this package is to implement
 * reusable, maintainable, and well-structured Page Object classes that abstract
 * away WebDriver interactions from test logic.
 * </p>
 *
 * <h2>📘 Package Overview</h2>
 * <ul>
 *   <li>Each page class should encapsulate web elements and user interactions.</li>
 *   <li>Business logic (test flows) should reside in the test classes — not in page objects.</li>
 *   <li>All locators should be private and exposed through clean methods.</li>
 *   <li>Every page should extend a common {@code BasePage} class (optional but recommended).</li>
 * </ul>
 *
 * <h2>🧩 Example: Page Object Class Structure</h2>
 * <pre>{@code
 * package pages;
 * 
 * import org.openqa.selenium.WebDriver;
 * import org.openqa.selenium.WebElement;
 * import org.openqa.selenium.support.FindBy;
 * import org.openqa.selenium.support.PageFactory;
 * import utils.WaitUtils;
 * import reports.Logger;
 * 
 * public class LoginPage {
 *     
 *     private WebDriver driver;
 * 
 *     // === Locators ===
 *     @FindBy(id = "username")
 *     private WebElement txtUsername;
 * 
 *     @FindBy(id = "password")
 *     private WebElement txtPassword;
 * 
 *     @FindBy(id = "loginBtn")
 *     private WebElement btnLogin;
 * 
 *     // === Constructor ===
 *     public LoginPage(WebDriver driver) {
 *         this.driver = driver;
 *         PageFactory.initElements(driver, this);
 *     }
 * 
 *     // === Actions ===
 *     public void enterUsername(String username) {
 *         WaitUtils.waitForVisibility(txtUsername).sendKeys(username);
 *         Logger.info("Entered username: " + username);
 *     }
 * 
 *     public void enterPassword(String password) {
 *         WaitUtils.waitForVisibility(txtPassword).sendKeys(password);
 *         Logger.info("Entered password");
 *     }
 * 
 *     public HomePage clickLogin() {
 *         WaitUtils.waitForElementToBeClickable(btnLogin).click();
 *         Logger.pass("Clicked on Login button");
 *         return new HomePage(driver);
 *     }
 * }
 * }</pre>
 *
 * <h2>🧠 Best Practices</h2>
 * <ul>
 *   <li>Keep methods atomic (e.g., one action per method like {@code enterUsername()}).</li>
 *   <li>Return the next page object after navigation actions (fluent design).</li>
 *   <li>Use {@code WaitUtils} for element synchronization.</li>
 *   <li>Use {@code Logger} for step reporting with or without screenshots.</li>
 *   <li>Avoid using {@code Thread.sleep()} — prefer explicit or fluent waits.</li>
 * </ul>
 *
 * <h2>📁 Recommended Folder Structure</h2>
 * <pre>
 * src
 * └── main
 *     └── java
 *         └── pages
 *             ├── LoginPage.java
 *             ├── HomePage.java
 *             ├── DashboardPage.java
 *             └── package-info.java
 * </pre>
 *
 * <h2>🔗 Related Packages</h2>
 * <ul>
 *   <li>{@link base} – contains {@code BaseClass} for setup/teardown</li>
 *   <li>{@link utils} – contains reusable utility classes like {@code WaitUtils}, {@code ExcelUtils}</li>
 *   <li>{@link driver} – manages WebDriver lifecycle and browser options</li>
 *   <li>{@link reports} – manages ExtentReports and logging</li>
 * </ul>
 *
 * @author
 * @version 1.0
 * 
 */
package pages;