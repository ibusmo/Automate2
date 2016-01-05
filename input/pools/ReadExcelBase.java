package pools;

import java.io.FileInputStream;

import testdata.CellTag.col;
import testdata.CellTag.fieldType;
import testdata.CellTag.inputType;
import testdata.CellTag.sheetType;

public abstract class ReadExcelBase implements ReadExcelController {
	
	String WorkBookPath;
	String WorkSheetPath;
	FileInputStream fileInputStream;

	@Override
	public sheetType getSheetType(col colTag, int rowTag) {
		String sheetStr = getString(colTag, rowTag).toLowerCase();
		/////////////////////////////////////////////////////////////////////////////////////// AUTO
		if(sheetStr.matches("ncb"))						return sheetType.NCB;

		else if(sheetStr.matches("longtermloan"))		return sheetType.LongTermLoan;
		else if(sheetStr.matches("promissorynote"))		return sheetType.PromissoryNote;
		else if(sheetStr.matches("bookgarantee"))		return sheetType.BookGarantee;
		else if(sheetStr.matches("od"))					return sheetType.OD;
		
		else if(sheetStr.matches("landandbuilding"))	return sheetType.CMSLandBuilding;
		else if(sheetStr.matches("building"))			return sheetType.CMSBuilding;
		else if(sheetStr.matches("land"))				return sheetType.CMSLand;
		else if(sheetStr.matches("accounting"))			return sheetType.CMSAccounting;
		else if(sheetStr.matches("lottery"))			return sheetType.CMSLottery;
		else if(sheetStr.matches("normal_warranter"))	return sheetType.CMSNormalWar;		
		else if(sheetStr.matches("legal_warranter"))	return sheetType.CMSLegalWar;

		else if(sheetStr.matches("normal"))				return sheetType.CutomerNormal;
		else if(sheetStr.matches("legal"))				return sheetType.CutomerLegal;

		else if(sheetStr.matches("ca"))					return sheetType.CA;
		else if(sheetStr.matches("credit"))				return sheetType.Credit;
		else if(sheetStr.matches("commitment"))			return sheetType.Commitment;

		if(sheetStr.matches("bro"))						return sheetType.BRO;
		if(sheetStr.matches("sbro"))					return sheetType.SBRO;
		
		else if(sheetStr.matches("cms"))				return sheetType.CMS;
		
		else if(sheetStr.matches("sbrosec"))			return sheetType.SBROSec;
		else if(sheetStr.matches("cm"))					return sheetType.CM;
		else if(sheetStr.matches("cmdept"))				return sheetType.CMDept;
		else if(sheetStr.matches("cmsec"))				return sheetType.CMSec;
		else if(sheetStr.matches("cmgr"))				return sheetType.CMGR;
		else if(sheetStr.matches("caco"))				return sheetType.CACO;
		
		else if(sheetStr.matches("ksccom"))				return sheetType.KSCCOM;
		else if(sheetStr.matches("rcom"))				return sheetType.RCOM;
		else if(sheetStr.matches("bcom"))				return sheetType.BCOM;
		else if(sheetStr.matches("rcom admin"))			return sheetType.RCOMAdmin;
		else if(sheetStr.matches("bcom admin"))			return sheetType.BCOMAdmin;
		
		else if(sheetStr.matches("mmgr"))				return sheetType.MMGR;
		else if(sheetStr.matches("aom"))				return sheetType.AOM;
		
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

	@Override
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
				
			case "urlMatches" :
				return fieldType.urlMatches;
			case "urlContains" :
				return fieldType.urlContains;
	
			case "textMatchesById" :
				return fieldType.textMatchesById;
			case "textMatchesByName" :
				return fieldType.textMatchesByName;
			case "textMatchesByXpath" :
				return fieldType.textMatchesByXpath;
			case "textMatchesByLinkText" :
				return fieldType.textMatchesByLinkText;
				
			case "textContainsById" :
				return fieldType.textContainsById;
			case "textContainsByName" :
				return fieldType.textContainsByName;
			case "textContainsByXpath" :
				return fieldType.textContainsByXpath;
			case "textContainsByLinkText" :
				return fieldType.textContainsByLinkText;
			
			case "valueMatchesById" :
				return fieldType.valueMatchesById;
			case "valueMatchesByName" :
				return fieldType.valueMatchesByName;
			case "valueMatchesByXpath" :
				return fieldType.valueMatchesByXpath;
			case "valueMatchesByLinkText" :
				return fieldType.valueMatchesByLinkText;
				
			case "valueContainsById" :
				return fieldType.valueContainsById;
			case "valueContainsByName" :
				return fieldType.valueContainsByName;
			case "valueContainsByXpath" :
				return fieldType.valueContainsByXpath;
			case "valueContainsByLinkText" :
				return fieldType.valueContainsByLinkText;
		}
		return null;
	}

	@Override
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

	@Override
	public inputType getIOType(col colTag, int rowTag) {
		String typStr = getString(colTag, rowTag);
		switch(typStr){
		case "dropdownx" :
			return inputType.dropdownx;
		case "dropdown" :
			return inputType.dropdown;
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

		case "savedraft" :
			return inputType.savedraft;
		case "save" :
			return inputType.save;
		case "verify" :
			return inputType.verify;
		case "jsexe" :
			return inputType.jsexe;
		case "openbrowser" :
			return inputType.openbrowser;
		}
		return null;
	}
	
	protected int getColumnMap(col colTag) {	
		char colChar = colTag.toString().charAt(0);
//		if(colChar>='A' && colChar<='Z')
//			return colTag.toString().charAt(0)-'A';
//		else
//			return colTag.toString().charAt(0)-'a';
		return colChar>='A' && colChar<='Z' ? colChar-'A' : colChar-'a';
	}
	
	protected int getRowMap(int row) {
		return row-1;
	}

}
