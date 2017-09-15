package com.honda.vmc.utc.utils;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileOperations {
	/**
	 * 
	 * @param path
	 * @return java.util.Map
	 * 
	 */
	public static void main(String[] args) {
		System.out.println("Main");
		String filePath = "D:\\Users\\prakash.gour\\Desktop\\vmc\\VMC_Test_Plan.xlsx";
		int colNum = 0;
		int sheetNum = 1;
		int parentNode = 5;

		FileOperations.readExcel(filePath, colNum, sheetNum, parentNode);
	}

	public static void readExcel(String filePath, int colNum, int sheetNum, int parentNode) {

		try {

			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = (Sheet) workbook.getSheetAt(sheetNum);
			System.out.println("Sheet name " + sheet.getSheetName());
			Iterator<Row> it = sheet.iterator();

			while (it.hasNext()) {

				Row row = it.next();
				Cell cell = row.getCell(colNum);

				if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
					String node = cell.getStringCellValue();

					if (node.equals("NA")) {
						System.out.println("Reached out to EOF");
						break;
					}

				} else if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					int node = Double.valueOf(cell.getNumericCellValue()).intValue();

					if (node == (parentNode + 1)) {
						System.out.println(row);

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
