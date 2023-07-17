package utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataInputProvider {
    public static Object[][] readExcelData(String dataSheetName) {
        Object[][] data = null;

        try {
            FileInputStream fis = new FileInputStream("./data/" + dataSheetName + ".xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            // get the number of rows
            int rowCount = sheet.getLastRowNum();

            // get the number of columns
            int columnCount = sheet.getRow(0).getLastCellNum();
            data = new Object[rowCount][columnCount];

            // loop through the rows
            for (int i = 1; i < rowCount + 1; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    for (int j = 0; j < columnCount; j++) { // loop through the columns
                        try {
                            Cell cell = row.getCell(j);
                            Object cellValue;
                            if (cell.getCellType() == CellType.STRING) {
                                cellValue = cell.getStringCellValue();
                            } else if (cell.getCellType() == CellType.NUMERIC) {
                                if (cell.getNumericCellValue() % 1 == 0) {
                                    cellValue = (int) cell.getNumericCellValue();
                                } else {
                                    cellValue = cell.getNumericCellValue();
                                }
                            } else {
                                cellValue = null; // or handle other cell types as needed
                            }

                            data[i - 1][j] = cellValue; // add to the data array
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fis.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}