package constants;

import java.io.File;

/**
 * Utility class that holds constant paths and file locations used throughout the automation framework.
 * <p>
 * This class centralizes project-related paths such as source folders, resource folders, reports, 
 * screenshots, configuration files, test data, and feature files. All paths are constructed dynamically 
 * based on the project root directory and the operating system file separator.
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class Constants {
    
    /** Root path of the project */
    private static String _ProjectPath = System.getProperty("user.dir");

    /** File separator based on the operating system */
    private static String _Seperator = File.separator;

    /** Path to the 'src' folder */
    private static String _SourcePath = _ProjectPath + _Seperator + "src";

    /** Path to the 'main' source folder */
    private static String _MainPath = _SourcePath + _Seperator + "main";

    /** Path to the 'test' source folder */
    private static String _TestPath = _SourcePath + _Seperator + "test";

    /** Path to the 'resources' folder inside main */
    private static String _MainResourcePath = _MainPath + _Seperator + "resources";

    /** Path to the 'resources' folder inside test */
    private static String _TestResourcePath = _TestPath + _Seperator + "resources";

    /** Folder path to store test reports */
    public static String _ReportsFolderPath = _ProjectPath + _Seperator + "Reports";

    /** Folder path to store screenshots */
    public static String _ScreenShotsFolderPath = _ProjectPath + _Seperator + "ScreenShots";

    /** Full path to the configuration properties file */
    public static String _ConfigPropertiesFilePath = _TestResourcePath + _Seperator + "config.properties";

    /** Folder path containing test data files */
    public static String _TestDataFolderPath = _TestResourcePath + _Seperator + "testdatas";

    /** Folder path containing feature files for Cucumber tests */
    public static String _FeatureFileFolderPth = _TestResourcePath + _Seperator + "features";

}
