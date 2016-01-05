package pools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import testdata.CellTag.col;

public class ReadExcelXLSX extends ReadExcelBase {
	
	HSSFWorkbook workbook;
	HSSFSheet worksheet;
	
	public ReadExcelXLSX(String WorkBookPath, String WorkSheetPath){
		this.WorkBookPath = WorkBookPath;
		this.WorkSheetPath = WorkSheetPath;
		openFile();
	}

	@Override
	public boolean openFile() {
		try {
			fileInputStream = new FileInputStream(WorkBookPath);
			workbook = new HSSFWorkbook(fileInputStream);
			worksheet = workbook.getSheet(WorkSheetPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean closeFile() {
		try {
			workbook.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String getString(col colTag, int rowTag) {
		int col = getColumnMap(colTag);
		int row = getRowMap(rowTag);
		HSSFRow rowTmp = worksheet.getRow(row);		
		HSSFCell cellTmp = rowTmp.getCell(col);
		String data;
		try{
			data = cellTmp==null ? "null" : cellTmp.getRichStringCellValue().toString();
		}catch(IllegalStateException e){
			data = cellTmp==null ? "null" : cellTmp.toString();
		}
		return data;
	}
	
}
