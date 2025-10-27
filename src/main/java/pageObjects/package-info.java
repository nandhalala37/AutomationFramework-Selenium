/**
 * Contains Page Object interfaces that define locators for web elements.
 * <p>
 * The purpose of this package is to separate element locators from page actions.
 * Each interface typically represents a page or a component and declares locators
 * as {@code By} constants or strings. This approach enhances maintainability
 * and reduces duplication when locators are shared across multiple page classes.
 * </p>
 *
 * <h2>📘 Package Overview</h2>
 * <ul>
 *   <li>All interfaces store locators for a specific page or module.</li>
 *   <li>Locators are declared as {@code By} constants (recommended) or Strings.</li>
 *   <li>Action classes (in {@code pages} package) reference these interfaces.</li>
 *   <li>Interfaces should not contain methods — only locators.</li>
 * </ul>
 *
 * <h2>🧩 Example: Page Object Interface Structure</h2>
 * <pre>{@code
 * package pageObjects;
 * 
 * import org.openqa.selenium.By;
 * 
 * public interface LoginPageLocators {
 *     
 *     By txtUsername = By.id("username");
 *     By txtPassword = By.id("password");
 *     By btnLogin = By.id("loginBtn");
 *     By lblErrorMessage = By.xpath("//div[@class='error']");
 * }
 * }</pre>
 *
 * <h2>🧠 Best Practices</h2>
 * <ul>
 *   <li>Keep locators private to the interface but public static by default.</li>
 *   <li>Do not include action methods here; only store locators.</li>
 *   <li>Use descriptive names that clearly indicate element purpose.</li>
 *   <li>Reference these locators in Page classes for actions and logging.</li>
 *   <li>Maintain a consistent naming convention across all interfaces.</li>
 * </ul>
 *
 * <h2>📁 Recommended Folder Structure</h2>
 * <pre>
 * src
 * └── test
 *     └── java
 *         └── pageObjects
 *             ├── LoginPageLocators.java
 *             ├── HomePageLocators.java
 *             ├── DashboardPageLocators.java
 *             └── package-info.java
 * </pre>
 *
 * <h2>🔗 Related Packages</h2>
 * <ul>
 *   <li>{@link pages} – contains page classes implementing actions using these locators</li>
 *   <li>{@link utils} – contains helper methods like {@code WaitUtils} for waiting on elements</li>
 *   <li>{@link reports} – logging and reporting utilities that can be used with page actions</li>
 * </ul>
 *
 * @author
 *     Automation Framework Team
 * @version 1.0
 * 
 */
package pageObjects;