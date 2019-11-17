package com.demoapp.testauto.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	/**
	 * Method for count number of ROW
	 * 
	 * @param xlPath
	 * @param sheetName
	 * @return
	 */
	public static int getRowCount(String xlPath, String sheetName) {
		int rc = 0;
		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = WorkbookFactory.create(fis);
			rc = wb.getSheet(sheetName).getLastRowNum();
		} catch (Exception e) {

		}
		return rc;
	}

	/**
	 * Read from a excel sheet
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public static String getCellValue(String xlPath, String sheetName, int rowNum, int cellNum) {
		String cellValue = " ";

		try {
			FileInputStream fis = new FileInputStream(xlPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s1 = wb.getSheet(sheetName);
			cellValue = s1.getRow(rowNum).getCell(cellNum).getStringCellValue();
		}

		catch (Exception e) {

		}

		return cellValue;
	}

	/**
	 * Write in excel sheet
	 * 
	 * @param filepath
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @param data
	 */
	public static void setData(String filepath, String sheetName, int rowNum, int cellNum, String data) {
		try {
			FileInputStream fis = new FileInputStream(filepath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet(sheetName);
			sheet.createRow(rowNum).createCell(cellNum).setCellValue(data);
			FileOutputStream fos = new FileOutputStream(filepath);
			wb.write(fos);

		} catch (Exception e) {

		}
	}

}
