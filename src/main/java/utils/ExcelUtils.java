package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

/**
 * ExcelUtils provides methods to read data from Excel files (.xlsx).
 */
public class ExcelUtils {

    /**
     * Reads data from the specified Excel file and sheet.
     *
     * @param fileName Excel file in resources/testdata (e.g., "data.xlsx")
     * @param sheetName Name of the sheet to read
     * @return List of Map representing each row with column headers as keys
     */
    public static List<Map<String, String>> readExcelData(String fileName, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();

        try (InputStream inputStream = ExcelUtils.class.getClassLoader().getResourceAsStream("testdata/" + fileName);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                Map<String, String> rowMap = new LinkedHashMap<>();

                for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = currentRow.getCell(j).toString();
                    rowMap.put(key, value);
                }

                data.add(rowMap);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + fileName, e);
        }

        return data;
    }
}
