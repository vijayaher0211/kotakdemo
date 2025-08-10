package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * GetTestData
 * 
 * Utility class to read test data from an Excel file (.xlsx or .xls)
 * using Apache POI library. It loads the specified sheet and returns
 * the data as a two-dimensional Object array for use in data-driven tests.
 * 
 * The Excel file path is read from the configuration file.
 * 
 */



public class GetTestData {

	public static Object[][] getTestData(String sheetName) {
	    String filePath = ReadConfigFile.get("testDataFilePath");

	    try (InputStream file = GetTestData.class.getClassLoader().getResourceAsStream(filePath)) {
	        if (file == null) {
	            throw new RuntimeException("Test data file not found: " + filePath);
	        }
	        Workbook workbook = WorkbookFactory.create(file);
	        Sheet sheet = workbook.getSheet(sheetName);
	        if (sheet == null) {
	            throw new RuntimeException("Sheet not found: " + sheetName);
	        }

	        int rowCount = sheet.getLastRowNum();
	        int colCount = sheet.getRow(0).getLastCellNum();

	        Object[][] data = new Object[rowCount][colCount];
	        DataFormatter formatter = new DataFormatter();

	        for (int i = 0; i < rowCount; i++) {
	            for (int j = 0; j < colCount; j++) {
	                data[i][j] = formatter.formatCellValue(sheet.getRow(i + 1).getCell(j)).trim();
	            }
	        }

	        workbook.close();
	        return data;

	    } catch (IOException e) {
	        throw new RuntimeException("Failed to read test data from Excel file: " + filePath, e);
	    }
	}

	
	
}
