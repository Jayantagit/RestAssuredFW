package Utitility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadExcelData {

	private static XSSFWorkbook book;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	private static XSSFCell cell;

	public static Object[] getEnabledExcelTests(String excelFileName, String sheetName) {
		return getEnabledExcelTests(excelFileName, sheetName, "executionFlag");
	}

	private static Object[] getEnabledExcelTests(String excelFileName, String sheetName, String executionFlag) {

		String file = System.getProperty("user.dir") +"\\src\\test\\resources\\Data\\Excel\\"+ excelFileName;
		sheet = readExcel(file, sheetName);
		List<Map<String, Object>> dataList = getDataAsList(sheet);
		List<Object> enabledTests = new ArrayList<Object>();
		int i = 0;
		while (i < dataList.size()) {
			if (dataList.get(i).get(executionFlag) != null && dataList.get(i).get(executionFlag).equals("Y")) {
				enabledTests.add(dataList.get(i));
			}
			i++;

		}
		return enabledTests.toArray();
	}

	private static XSSFSheet readExcel(String excelFileName, String sheetName) {

		FileInputStream ip;
		try {
			ip = new FileInputStream(new File(excelFileName));
			book = new XSSFWorkbook(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return book.getSheet(sheetName);

	}

	private static List<Map<String, Object>> getDataAsList(XSSFSheet sheet) {

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		int rowCount = sheet.getLastRowNum();

		if (rowCount > 0) 
		{
			//Adding Column Headers to List
			List<String> headers = new ArrayList<String>();
			row = sheet.getRow(0);
			int colCount = row.getPhysicalNumberOfCells();
			for (int col = 0; col < colCount; col++) {
				cell = row.getCell(col);
				if (cell.getCellType() == CellType.STRING) {
					headers.add(cell.getStringCellValue());
				}
			}
			
			DataFormatter format=new DataFormatter();
			
			for(int i=1;i<=rowCount;i++)
			{
				Map<String,Object> map=new LinkedHashMap<String,Object>();
				row=sheet.getRow(i);
				if(row.getCell(i)!=null)
				{
					for(int j=0;j<headers.size();j++)
					{
						cell=row.getCell(j);
						
						if(cell.getCellType()==CellType._NONE)
						{
							map.put(headers.get(j), null);
						}
						else if(cell.getCellType()==CellType.STRING)
						{
							map.put(headers.get(j), cell.getStringCellValue());
						}
						else if(cell.getCellType()==CellType.NUMERIC)
						{
							map.put(headers.get(j), format.formatCellValue(cell));
						}
						else if(cell.getCellType()==CellType.BLANK)
						{
							map.put(headers.get(j), "");
						}
					}
					
				}
				dataList.add(map);
			}
		}

		return dataList;
	}

}
