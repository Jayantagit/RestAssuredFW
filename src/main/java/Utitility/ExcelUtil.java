package Utitility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static XSSFWorkbook book;
	private static XSSFSheet sheet;

	private static String TEST_DATA_PATH = "./src/test/resources/Data/TestData.xlsx";

	public static Object[][] getTestData(String sheetName) {

		Object data[][] = null;

		try {
			FileInputStream ip = new FileInputStream(new File(TEST_DATA_PATH));
			book = new XSSFWorkbook(ip);
			sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}

		}
		return data;

	}

}
