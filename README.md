# Selenium Java Automation Framework

A comprehensive, modular, and maintainable automation framework using **Selenium WebDriver**, **TestNG**, **ExtentReports**, **Apache POI**, and **Page Object Model (POM)** principles.  
Supports **local and grid execution**, **parallel tests**, **multiple browsers**, **headless mode**, and **data-driven testing**.

---

## 🔹 Features

- **Browser Support:** Chrome, Firefox, Edge, Safari
- **Execution Modes:** Local & Selenium Grid
- **Parallel Execution:** Configurable via `config.properties` (`threadCount` & `parallel`)
- **Headless Mode:** Configurable via properties
- **Page Object Model:** Clean separation of page actions and locators
- **Reporting:** ExtentReports with screenshots on failure/pass/info
- **Data-Driven Testing:** Excel integration using Apache POI
- **Utilities:** Custom utilities for waits, actions, alerts, scrolling, and JavaScript
- **Logging:** Step-level logging using ExtentReports
- **Framework Configuration:** Centralized in `config.properties`
- **Cross-platform:** Works on Windows, MacOS, Linux

---

## 🔹 Project Structure

```
src
 └── main
 └── test
     ├── java
     │   ├── base              # BaseClass for setup/teardown
     │   ├── config            # ConfigReader
     │   ├── constants         # Constants & Enum keys
     │   ├── driver            # WebDriver managers
     │   ├── listener          # TestNG Listeners
     │   ├── pages             # Page classes implementing actions
     │   ├── pageObjects       # Locator interfaces
     │   ├── reports           # Logging & ExtentReports management
     │   └── utils             # Utilities: Wait, Excel, Driver, Screenshots
     └── resources
         ├── config.properties # Framework configurations
         ├── testdatas         # Excel test data
         └── features          # Optional Cucumber feature files
```

---

## 🔹 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repo-url>
   cd <project-folder>
   ```

2. **Install Java & Maven**
   - Java JDK 11+
   - Maven 3.6+

3. **Configure properties**
   - Open `src/test/resources/config.properties`
   - Update:
     ```properties
     execution=local
     browser=chrome
     baseURL=https://example.com
     parallel=true
     threadCount=3
     headless=false
     implicitWait=10
     ```

4. **Add Excel Test Data**
   - Place test data in `src/test/resources/testdatas`
   - Use `ExcelUtils` to read data.

5. **Add browser drivers**
   - ChromeDriver, GeckoDriver, EdgeDriver, SafariDriver should be available in system path or managed via WebDriverManager.

---

## 🔹 Running Tests

### Using TestNG XML
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Parallel Execution
- Set `parallel=true` and `threadCount=<number>` in `config.properties`.

### Headless Mode
- Set `headless=true` in `config.properties`.

### Selenium Grid Execution
- Set `execution=grid` and provide `gridURL` in `config.properties`.

---

## 🔹 Utilities & Helper Classes

| Utility | Purpose |
|---------|---------|
| `DriverUtils` | Common WebElement actions, JS execution, scrolling, alerts, window/tab handling |
| `WaitUtils` | Explicit and fluent waits |
| `ExcelUtils` | Read Excel data as Map/List |
| `ScreenshotUtils` | Capture screenshots and return Base64 strings |
| `Logger` | Log steps to ExtentReports with optional screenshots |
| `TestManager` | Thread-safe management of ExtentTest instances |
| `DriverManager` | Thread-safe WebDriver management for multiple browsers |

---

## 🔹 Reporting

- Reports are generated in `Reports/<timestamp>` folder.
- Each test has its own HTML report.
- Screenshots are automatically captured for failed steps.
- Example:
```
Reports/
 └── 20251027_143210/
     ├── RegressionSuite_ExtentReport.html
     └── Screenshots/
```

---

## 🔹 Best Practices

- Keep Page classes focused on **actions**, locators should reside in **`pageObjects` interfaces**.
- Use **`WaitUtils`** instead of `Thread.sleep()`.
- Use **`Logger`** for all step reporting.
- Always close the **WebDriver** in `@AfterMethod`.
- Maintain **atomic and reusable page methods**.
- Use **ExcelUtils** for all test data; avoid hard-coded values.

---

## 🔹 Dependencies

- Selenium WebDriver
- TestNG
- Apache POI
- ExtentReports
- WebDriverManager (optional for automatic driver management)
- Apache Commons IO

---

## 🔹 Notes

- Supports **multi-threaded execution** for CI/CD pipelines.
- Easy to extend for **Cucumber BDD** by adding step definitions.
- Compatible with **Windows, Mac, Linux** browsers.

---

## 🔹 Author


Version: 1.0  
Date: 
