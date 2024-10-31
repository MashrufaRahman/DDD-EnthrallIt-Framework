package dddUnitTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;

import freemarker.template.utility.Execute;

public class ExcelReadingTest {

	@Test
	// workbook and excel sheet is same
	// we got value of a cell from this below method
	public void readDataByInputStream() throws EncryptedDocumentException, IOException {
		// Alternate of input stream is by showing File location below
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("LoginDataForDDDFramework.xlsx");
		// make sure you choose the .ss one
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(2);
		Cell cell = row.getCell(1);
		System.out.println(cell.getStringCellValue());
		// all data in the excel sheet String type
	}

	@Test

	// we got values of a row from the below method
	public void readDataByFile() throws EncryptedDocumentException, IOException {
		File file = new File(
				"C:\\Users\\mashr\\eclipse-workspace\\com.enthrallit_2024\\src\\test\\resources\\LoginDataforDDDFramework.xlsx");
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(1);
		// avoid null value, so we choose the below method
		int totalCell = row.getLastCellNum();
		System.out.println("Total cells: " + totalCell);

		List<Object> rowData = new ArrayList<Object>();

		for (int i = 0; i < totalCell; i++) {

			Cell cell = row.getCell(i);
			// in excel sheet reader read as a string but if we have int data then we have
			// to cast it
			if (cell != null) {
				if (cell.getCellType() == CellType.NUMERIC) {
					rowData.add((int) cell.getNumericCellValue()); // cast by int(int)

				} else {
					rowData.add(cell.getStringCellValue());
				}
			} else {
				rowData.add(cell); // it will add null
			}
		}
		System.out.println(rowData);
	}

	@Test
	public void readDataByMap() throws EncryptedDocumentException, IOException {
		// File file = new
		// File("C:\\Users\\mashr\\eclipse-workspace\\com.enthrallit_2024\\src\\test\\resources\\LoginDataforDDDFramework.xlsx");
		// Workbook workbook = WorkbookFactory.create(file);
		// or
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("LoginDataForDDDFramework.xlsx");
		// make sure you choose the .ss one
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet("Sheet1");
		int totalRows = sheet.getLastRowNum();
		System.out.println("Total rows : " + totalRows);

		List<Map<Object, Object>> sheetData = new ArrayList<Map<Object, Object>>();

		// 0 contain header thats why we use 1
		for (int i = 1; i <= totalRows; i++) {
			Row row = sheet.getRow(i);

			// why we used linkedhashMap ? to get data index wise,only HashMap choose data
			// randomly
			// why we write object? because we can retrieve various data type like Strint,
			// int, boolean
			Map<Object, Object> rowMap = new LinkedHashMap<>();

			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				if (cell != null) {
					Object key = sheet.getRow(0).getCell(j).getRichStringCellValue();
					Object value = null;

					if (cell.getCellType() == CellType.NUMERIC) {
						value = (int) cell.getNumericCellValue();

					} else if (cell.getCellType() == CellType.STRING) {
						value = cell.getStringCellValue();

					} else if (cell.getCellType() == CellType.BOOLEAN) {
						value = cell.getBooleanCellValue();

					}
					// we add data in Map by put() method
					rowMap.put(key, value);
				}

			}

			if (rowMap.get("Execute") != null) {
				if (String.valueOf(rowMap.get("Execute")).trim().equalsIgnoreCase("Y"))
					;
				sheetData.add(rowMap);
			}
		}

		// java lambda is used
		sheetData.forEach(System.out::println);
		// to find out one row by get method of Map
		// System.err.println(sheetData.get(0).get("Password"));
		// system.err.println(sheetData.get(0).get(password)

	}

}
