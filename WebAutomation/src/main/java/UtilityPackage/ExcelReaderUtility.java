package UtilityPackage;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {
    
    public static Object[][] ExcelRead() throws IOException {
        // Use try-with-resources to ensure proper closure of resources
        try (FileInputStream fis = new FileInputStream("C:\\Users\\febaz\\git\\WebApplicationAutomation\\WebAutomation\\src\\main\\resource\\TestData\\Login.xlsx");
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            if (rowCount <= 1) {
                return new Object[0][0];
            }

            Row headerRow = sheet.getRow(0);
            int cellCount = headerRow.getPhysicalNumberOfCells();

            Object[][] credentials = new Object[rowCount - 1][cellCount];
            for (int i = 1; i < rowCount; i++) { // Skip header row
                Row row = sheet.getRow(i);
                for (int j = 0; j < cellCount; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        credentials[i - 1][j] = cell.toString(); // Convert cell to string representation
                    } else {
                        credentials[i - 1][j] = null;
                    }
                }
            }

            return credentials;
        }
    }
}