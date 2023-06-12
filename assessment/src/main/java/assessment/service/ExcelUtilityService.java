package assessment.service;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Kripal
 * 
 * Excelsheet XSSF (.xlsx) file reader and convert to CSV 
 * 
 */
public class ExcelUtilityService {

	/**
	 * This function is to create CSV file from XLSX file
	 * @param	FileInputStream xlsxFile
	 * @return	String filepath
	 */
	public static void csvConverter(String filePath, String outputCSV) throws IOException {
		System.out.println("Read XLSX start.." + new Date().getTime());
		FileWriter fw = new FileWriter(outputCSV);
		Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));
		System.out.println("Read XLSX end.." + new Date().getTime());
		System.out.println("Write CSV start.." + new Date().getTime());
		Sheet sheet = wb.getSheetAt(0);
		Row row = null;
		int rowCount = sheet.getLastRowNum() + 1;	// Including header (+1)
		System.out.println("Total Row count: " + (rowCount - 1));
		for (int i = 0; i < rowCount; i++) {
			row = sheet.getRow(i);
			String rowString = new String();
			int cellCount = row.getLastCellNum();
			for (int j = 0; j < cellCount; j++) {
				if (row.getCell(j) != null) {
					if(j < (cellCount - 1) && CellType.NUMERIC.equals(row.getCell(j).getCellType()))
						rowString += (long)row.getCell(j).getNumericCellValue();
					else
						rowString += row.getCell(j);
				}
				rowString += ",";
			}
			fw.write(rowString.substring(0, rowString.length() - 1) + (i < rowCount - 1 ? "\n" : ""));
		}
		System.out.println("Write CSV end.." + new Date().getTime());
		fw.close();
		wb.close();
	}
}
