package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Utility class for handling Excel file operations using Apache POI.
 * <p>
 * This class provides methods to read test data from Excel files in both
 * tabular (list of maps) and individual cell formats. It is commonly used
 * in data-driven testing frameworks.
 * </p>
 *
 * <p><b>Features:</b></p>
 * <ul>
 *   <li>Reads Excel files (.xlsx format) dynamically</li>
 *   <li>Supports fetching data as a list of key-value pairs (column header → cell value)</li>
 *   <li>Allows direct cell lookup by row number and column header</li>
 *   <li>Automatically handles null and empty rows</li>
 *   <li>Graceful resource cleanup via {@link #closeWorkbook()}</li>
 * </ul>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>
 * ExcelUtils excel = new ExcelUtils("testdata/LoginData.xlsx", "Sheet1");
 * List&lt;Map&lt;String, String&gt;&gt; data = excel.getDataAsListOfMaps();
 * String username = data.get(0).get("Username");
 * excel.closeWorkbook();
 * </pre>
 *
 * @author 
 * @version 1.0
 */
public class ExcelUtils {

    /** The Apache POI Workbook instance representing the Excel file. */
    private Workbook workbook;

    /** The specific Sheet instance within the Excel workbook. */
    private Sheet sheet;

    /**
     * Constructs an {@code ExcelUtils} instance and loads the specified Excel sheet.
     *
     * @param filePath  The absolute or relative path to the Excel file
     * @param sheetName The name of the sheet to load
     * @throws RuntimeException if the file or sheet cannot be found or loaded
     */
    public ExcelUtils(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load Excel file: " + e.getMessage());
        }
    }

    /**
     * Reads all rows from the loaded Excel sheet and returns them as a list of maps.
     * <p>
     * Each map represents a single row where:
     * <ul>
     *   <li>Key = Column header name (from the first row)</li>
     *   <li>Value = Corresponding cell value (as a string)</li>
     * </ul>
     * </p>
     *
     * @return A {@code List<Map<String, String>>} containing all data rows
     * @throws RuntimeException if the header row is missing
     */
    public List<Map<String, String>> getDataAsListOfMaps() {
        List<Map<String, String>> allData = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            throw new RuntimeException("No header row found in sheet: " + sheet.getSheetName());
        }

        int totalRows = sheet.getLastRowNum();
        int totalCols = headerRow.getLastCellNum();

        for (int i = 1; i <= totalRows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> rowData = new LinkedHashMap<>();
            for (int j = 0; j < totalCols; j++) {
                String key = formatter.formatCellValue(headerRow.getCell(j));
                String value = formatter.formatCellValue(row.getCell(j));
                rowData.put(key, value);
            }
            allData.add(rowData);
        }
        return allData;
    }

    /**
     * Retrieves the value of a specific cell using the given row number and column header.
     *
     * @param rowNum      The row number (starting from 1 for first data row)
     * @param columnName  The header name of the desired column
     * @return The cell value as a string, or {@code null} if not found
     */
    public String getCellData(int rowNum, String columnName) {
        Row headerRow = sheet.getRow(0);
        DataFormatter formatter = new DataFormatter();
        int totalCols = headerRow.getLastCellNum();

        for (int i = 0; i < totalCols; i++) {
            String header = formatter.formatCellValue(headerRow.getCell(i));
            if (header.equalsIgnoreCase(columnName)) {
                Row row = sheet.getRow(rowNum);
                return formatter.formatCellValue(row.getCell(i));
            }
        }
        return null;
    }

    /**
     * Closes the loaded Excel workbook and releases system resources.
     * <p>
     * Should be called after all operations to prevent resource leaks.
     * </p>
     */
    public void closeWorkbook() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
