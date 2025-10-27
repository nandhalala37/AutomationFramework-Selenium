package constants;

/**
 * Enum representing the keys used in the configuration properties file.
 * <p>
 * Each enum constant corresponds to a property key that can be retrieved
 * using {@link config.ConfigReader#getProperty(ConfigPropertiesKey)}.
 * </p>
 * 
 * @version 1.0
 */
public enum ConfigPropertiesKey {

    /** Execution mode (e.g., local or grid) */
    EXECUTION("execution"),

    /** Browser type (e.g., chrome, firefox) */
    BROWSER("browser"),

    /** URL of the Selenium Grid hub */
    GRIDURL("gridURL"),

    /** Base URL of the application under test */
    BASEURL("baseURL"),

    /** Title of the test report */
    REPORTTITLE("reportTitle"),

    /** Name of the test report file */
    REPORTNAME("reportName"),

    /** Whether parallel execution is enabled */
    PARALLEL("parallel"),

    /** Number of threads to use for parallel execution */
    THREADCOUNT("threadCount"),

    /** Whether to run the browser in headless mode */
    HEADLESS("headless"),

    /** Implicit wait timeout in seconds */
    WAITTIMEOUT("implicitWait");
    
    /** The string key corresponding to the property in the config file */
    private final String key;

    /**
     * Constructor to initialize the enum with the corresponding property key.
     *
     * @param key The property key as a {@link String}
     */
    ConfigPropertiesKey(String key) {
        this.key = key;
    }

    /**
     * Retrieves the string key associated with this enum constant.
     *
     * @return The property key as a {@link String}
     */
    public String getKey() {
        return key;
    }
    
}
