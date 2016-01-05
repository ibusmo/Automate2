package pools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import testdata.CellTag.col;
import testdata.CellTag.fieldType;
import testdata.CellTag.inputType;
import testdata.CellTag.sheetType;

public class ReadExcelXLSXBackup {
	
	String WorkBookPath;
	String WorkSheetPath;
	FileInputStream fileInputStream;
//	XSSFWorkbook workbook;
//	XSSFSheet worksheet;
	HSSFWorkbook workbook;
	HSSFSheet worksheet;
	
	public ReadExcelXLSXBackup(String WorkBookPath, String WorkSheetPath){
		this.WorkBookPath = WorkBookPath;
		this.WorkSheetPath = WorkSheetPath;
		openFile();
	}
	
	public sheetType getSheetType(col colTag, int rowTag){
		String sheetStr = getString(colTag, rowTag).toLowerCase();
		/////////////////////////////////////////////////////////////////////////////////////// AUTO
		if(sheetStr.matches("bro"))						return sheetType.BRO;
		else if(sheetStr.matches("ncb"))				return sheetType.NCB;
		
		else if(sheetStr.matches("com"))				return sheetType.LoanCOM;
		else if(sheetStr.matches("dda"))				return sheetType.LoanDDA;
		
		else if(sheetStr.matches("landandbuilding"))	return sheetType.CMSLandBuilding;
		else if(sheetStr.matches("building"))			return sheetType.CMSBuilding;
		else if(sheetStr.matches("land"))				return sheetType.CMSLand;
		else if(sheetStr.matches("accounting"))			return sheetType.CMSAccounting;
		else if(sheetStr.matches("normal_war"))			return sheetType.CMSNormalWar;		
		else if(sheetStr.matches("legal_war"))			return sheetType.CMSLegalWar;
		
		else if(sheetStr.matches("normal"))				return sheetType.CutomerNormal;
		else if(sheetStr.matches("legal"))				return sheetType.CutomerLegal;
		
		else if(sheetStr.matches("cms"))				return sheetType.CMS;
		
		else if(sheetStr.matches("ca"))					return sheetType.CA;
		else if(sheetStr.matches("sbrosec"))			return sheetType.SBROSec;
		else if(sheetStr.matches("cmdept"))				return sheetType.CMDept;

		///////////////////////////////////////////////////////////////////////////////////// Manual
		else if(sheetStr.contains("lor"))				return sheetType.LOR;
		else if(sheetStr.contains("cms"))				return sheetType.CMS;
		else if(sheetStr.contains("login"))				return sheetType.Login;
		else if(sheetStr.contains("logout"))			return sheetType.Logout;

		else if(sheetStr.contains("register"))			return sheetType.Register;

		else if(sheetStr.contains("gotoapp"))			return sheetType.GotoApp;

		else if(sheetStr.contains("ncb_option"))		return sheetType.NCBOption;

		else if(sheetStr.contains("customer_org"))		return sheetType.AddLegalCustomer;
		else if(sheetStr.contains("customer"))			return sheetType.AddNormalCustomer;

		else if(sheetStr.contains("loanform_com"))		return sheetType.AddLoanCOM;
		else if(sheetStr.contains("loanform_dda"))		return sheetType.AddLoanDDA;

		else if(sheetStr.contains("landbuilding"))		return sheetType.CollLandBuilding;
		else if(sheetStr.contains("building"))			return sheetType.CollBuilding;
		else if(sheetStr.contains("land"))				return sheetType.CollLand;
		else if(sheetStr.contains("addwarranter_org"))	return sheetType.CollLegalWarranter;
		else if(sheetStr.contains("addwarranter"))		return sheetType.CollNormalWarranter;

		else if(sheetStr.contains("valuation"))			return sheetType.CMSValuation;
		
		else if(sheetStr.contains("document"))			return sheetType.Document;
		else if(sheetStr.contains("sendwork"))			return sheetType.SendWork;
		else if(sheetStr.contains("endwork"))			return sheetType.EndWork;

		else if(sheetStr.contains("ncb_summary"))		return sheetType.NCBSummary;
		else if(sheetStr.contains("excutive"))			return sheetType.ExcutiveSummary;

		else if(sheetStr.contains("wait"))				return sheetType.Wait;

		else if(sheetStr.contains("searchworkbox"))		return sheetType.SearchWorkBox;
		
		else											return sheetType.None;
	}
	
	public boolean openFile(){
		try {
			fileInputStream = new FileInputStream(WorkBookPath);			
//			workbook = new XSSFWorkbook(fileInputStream);		
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
	
	public boolean closeFile(){
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

	public String getString(col colTag, int rowTag) {
		int col = getColumnMap(colTag);
		int row = getRowMap(rowTag);
//		XSSFRow rowTmp = worksheet.getRow(row);		
//		XSSFCell cellTmp = rowTmp.getCell(col);
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
	
	public fieldType getFieldType(col colTag, int rowTag) {
		String ioStr = getString(colTag, rowTag);
		switch(ioStr){
		case "id" :
			return fieldType.id;
		case "name" :
			return fieldType.name;
		case "xpath" :
			return fieldType.xpath;
		case "linktext" :
			return fieldType.linktext;
		}
		return null;
	}	
	
	public boolean getRun(col colTag, int rowTag) {
		String run = getString(colTag, rowTag);
		switch(run){
		case "false" :
			return false;
		case "true" :
			return true;
		}
		return false;
	}
	
	public inputType getIOType(col colTag, int rowTag) {
		String typStr = getString(colTag, rowTag);
		switch(typStr){
		case "dropdown" :
			return inputType.dropdown;
		case "dropdownx" :
			return inputType.dropdownx;
		case "date" :
			return inputType.date;
		case "text" :
			return inputType.text;
		case "radio" :
			return inputType.radio;
		case "button" :
			return inputType.button;
		case "checkbox" :
			return inputType.checkbox;
		case "alert" :
			return inputType.alert;
		case "popup" :
			return inputType.popup;
		}
		return null;
	}

	private int getColumnMap(col colTag) {	
		char colChar = colTag.toString().charAt(0);
		if(colChar>='A' && colChar<='Z')
			return colTag.toString().charAt(0)-'A';
		else
			return colTag.toString().charAt(0)-'a';
	}
	
	private int getRowMap(int row) {
		return row-1;
	}
}
