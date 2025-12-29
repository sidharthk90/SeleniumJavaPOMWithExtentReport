package org.SeleniumJavaPOM.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static Object[][] getSheetData(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(sheetName);

        int totalRows = sheet.getLastRowNum();
        int totalCols = sheet.getRow(0).getLastCellNum();

        List<Object[]> rowList = new ArrayList<>();

        for (int i = 1; i <= totalRows; i++) {
            Row row = sheet.getRow(i);

            if (row == null || isRowEmpty(row)) {
                continue;
            }

            Object[] rowData = new Object[totalCols];

            for (int j = 0; j < totalCols; j++) {
                Cell cell = row.getCell(j);

                if (cell == null || cell.getCellType() == CellType.BLANK) {
                    rowData[j] = "";
                } else {
                    cell.setCellType(CellType.STRING);
                    rowData[j] = cell.getStringCellValue();
                }
            }
            rowList.add(rowData);
        }

        wb.close();

        Object[][] data = new Object[rowList.size()][totalCols];
        for (int i = 0; i < rowList.size(); i++) {
            data[i] = rowList.get(i);
        }

        return data;
    }

    private static boolean isRowEmpty(Row row) {
        for (int c = 0; c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }


    public static List<String> readColumn(String resourcePath, String sheetName) {
        List<String> list = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream(resourcePath);

            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + resourcePath);
            }

            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheet(sheetName);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    list.add(cell.getStringCellValue().trim());
                }
            }

            wb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void writeList(String filePath, String sheetName, List<String> dataList) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet(sheetName);

            int rowNum = 0;
            for (String value : dataList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(value);
            }

            try (FileOutputStream fos = new FileOutputStream(file)) {
                wb.write(fos);
                System.out.println("Excel written to: " + file.getAbsolutePath());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
