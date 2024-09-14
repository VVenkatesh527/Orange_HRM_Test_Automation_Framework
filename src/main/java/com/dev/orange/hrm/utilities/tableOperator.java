package com.dev.orange.hrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.WebDriverWait;

public class tableOperator {

	public static File file = null;
	public static Properties prop = null;
	private static FileInputStream inputStream = null;
	public static WebDriverWait wait = null;
	public final static String defaultDirectoryPath = System.getProperty("user.dir");
	public final static String defaultPropertyPath = defaultDirectoryPath + ".\\src\\main\\resource\\";
	public final static String fileName = defaultDirectoryPath + "testData\\excelData.xlsx";

	public static HashMap<String, String> getExcelData() {

		Workbook workbook = null;
		Row row = null;

		ArrayList<String> headerList = new ArrayList<>();
		HashMap<String, String> fromData = new HashMap<String, String>();
		file = new File(fileName);
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		try {
			inputStream = new FileInputStream(file);
			if (fileExtensionName.equals(".xlsx")) {
				workbook = new XSSFWorkbook(inputStream);
			} else if (fileExtensionName.equals(".xls")) {
				workbook = new XSSFWorkbook(inputStream);
			}

			Sheet sheetName = workbook.getSheetAt(0);
			int rowNum = sheetName.getLastRowNum() - sheetName.getFirstRowNum();

			for (int r = 1; r < rowNum + 1; r++) {
				row = sheetName.getRow(r);
				int col = row.getLastCellNum();
				for (int c = 0; c < col; c++) {
					headerList.add(row.getCell(c).getStringCellValue());

				}
			}
			for (int r = 0; r < rowNum + 1; r++) {
				row = sheetName.getRow(r);
				int col = row.getLastCellNum();
				for (int c = 0; c < col; c++)
					fromData.put(headerList.get(c), row.getCell(c).getStringCellValue());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fromData;
	}
}
