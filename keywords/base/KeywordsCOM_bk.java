package base;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import controller.Controller;
import log.LogCat;
import log.LogTag.logaction;
import log.LogTag.logelement;
import log.LogTag.logexestatus;
import log.LogTag.logoperation;
import log.LogTag.logsubtab;
import log.LogTag.logtab;
import pools.ReadExcelController;
import pools.ReadExcelXLS;
import testdata.LoadElement;
import testdata.CellTag.col;
import testdata.CellTag.fieldType;
import testdata.DataElementObj;

public abstract class KeywordsCOM_bk{

//	protected EngineController commonDriver;
	
//	protected WebDriver driver;
//	protected WebDriverWait wait;
//	protected JavascriptExecutor executor;
	protected Controller ctrl;
	protected LogCat logCat;
	
	protected logoperation logoperation;
	protected logtab logtab;
	protected logsubtab logsubtab;

	protected String workBookPath;
	protected String workSheetPath;
	protected int sizeOfData;
	protected int offsetRow;
	
	protected LoadElement regData;
	protected List<DataElementObj> dataElementObjList;
	
	// TO BE OVERRIDE
	protected boolean preCondition(DataElementObj obj){return true;};
	protected boolean posCondition(DataElementObj obj){return true;};
	protected boolean preExecute(){return true;};
	protected boolean posExecute(){return true;};
	
	protected boolean initKeywords(){
		this.logCat = ctrl.logCat;
		this.workBookPath = ctrl.pathVariable.getExcelName();
		return true;
	}
	
	protected boolean loadData(){
		offsetRow = 2;
		sizeOfData = getSize();
		if(sizeOfData==-1){ return false; }
		regData = new LoadElement(workBookPath, workSheetPath, sizeOfData, offsetRow);
		if(regData.loadData()){
			dataElementObjList = regData.getObject();
			sendToLogCustom(logexestatus.PASS, logaction.LoadData, null);
		}else{
			sendToLogCustom(logexestatus.FAIL, logaction.LoadData, null);		
			return false;	
		}
		return true;
	}

	public boolean execute() {
		
		if(initKeywords()==false)					return false;
		if(loadData()==false) 						return false;
		
		sendToLogStart();
		if(preExecute()==false) 					return false;
		
		for(DataElementObj obj : dataElementObjList){
			preCondition(obj);
			if(obj.run==false) continue;
			try{
				switch(obj.type){
					case openbrowser:
						if(caseOpenBrowser(obj)==false) return false;
						break;
					case button:
						if(caseButton(obj)==false) 		return false;
						break;
					case dropdownx:
					case dropdown:
						if(caseDropdown(obj)==false)	return false;
						break;
					case text:
						if(caseText(obj)==false) 		return false;
						break;
					case radio:
						if(caseRadio(obj)==false)		return false;
						break;
					case checkbox:
						if(caseCheckbox(obj)==false) 	return false;
						break;
					case date:
						if(caseDate(obj)==false) 		return false;
						break;
					case popup:
						if(casePopup(obj)==false) 		return false;
						break;
					case alert:
						if(caseAlert(obj)==false) 		return false;
						break;
						
					case save:
					case savedraft:
						if(caseSave(obj)==false) 		return false;
						break;
					case verify:
						if(caseVerify(obj)==false) 		return false;
						break;
						
					case jsexe:
						if(caseJsExe(obj)==false) 		return false;
						break;				
				}
			}catch(NoSuchElementException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -NoSuchElementException");
				return false;
			}catch(UnreachableBrowserException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -UnreachableBrowserException");
				return false;
			}catch(InvalidElementStateException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -InvalidElementStateException");
				return false;
			}catch(NoAlertPresentException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -NoAlertPresentException");
				return false;
			}catch(TimeoutException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -TimeoutException");
				return false;
			}catch(UnhandledAlertException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -UnhandledAlertException");
				return false;
			}catch(WebDriverException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -WebDriverException");
				return false;
			}catch(NullPointerException e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -NullPointerException");
				return false;
			}catch(Exception e){
				sendToLogCustom(logexestatus.FAIL, logaction.None, " -Exception");				
			}
			posCondition(obj);
		}

		if(posExecute()==false) return false;
		sendToLogFinish();
		return true;
	}
	
	private boolean caseOpenBrowser(DataElementObj obj) {
		ctrl.driver.get(obj.fieldValue);
		ctrl.driver.manage().window().maximize();
		return true;
	}
	
	private boolean caseJsExe(DataElementObj obj) {
		try{
			ctrl.jsExecute.runExe(obj);
			sendToLogCustom(logexestatus.PASS, logaction.JSExe, obj.name + ": '" + obj.fieldName + " '");
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.JSExe, obj.name + ": '" + obj.fieldName + " ' -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.JSExe, obj.name + ": '" + obj.fieldName + " ' -InvalidElementStateException");
			return false;			
		}catch (UnhandledAlertException e){
			sendToLogCustom(logexestatus.FAIL, logaction.JSExe, obj.name + ": '" + obj.fieldName + " ' -UnhandledAlertException");
			return false;			
		}catch(NoSuchElementException e){
			sendToLogCustom(logexestatus.FAIL, logaction.JSExe, obj.name + ": '" + obj.fieldName + " ' -NoSuchElementException");
			return false;		
		}
		return true;
	}
	private boolean caseSave(DataElementObj obj) {
		try{
			ctrl.button.save(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Save, obj.name + ": " + obj.data);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.PASS, logaction.Save, obj.name + ": " + obj.data + "-TimeoutException");	
			return false;		
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.PASS, logaction.Save, obj.name + ": " + obj.data + "-InvalidElementStateException");	
			return false;				
		}catch(WebDriverException e){
			sendToLogCustom(logexestatus.PASS, logaction.Save, obj.name + ": " + obj.data + "-WebDriverException");	
			return false;	
		}
		return true;
	}
	
	private boolean caseVerify(DataElementObj obj) {
		try{
			if(ctrl.verifyData.runVerify(obj)){
				sendToLogCustom(logexestatus.PASS, logaction.Verify, obj.name + ": " + obj.data 
						+ " - " + obj.fieldType + ": '" + obj.fieldName + "'|'" + obj.fieldValue + "'");
			}
			else{
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, obj.name + ": " + obj.data  
						+ " - " + obj.fieldType + ": '" + obj.fieldName + "'|'" + obj.fieldValue + "'");
				return false;
			}
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Verify, obj.name + ": " + obj.data + " -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Verify, obj.name + ": " + obj.data + " -InvalidElementStateException");
			return false;			
		}catch (UnhandledAlertException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Verify, obj.name + ": " + obj.data + " -UnhandledAlertException");
			return false;			
		}
		return true;
	}
	protected boolean caseAlert(DataElementObj obj) {
		try{
			ctrl.alertHandle.RunAlert(obj);
//			new AlertHandle(commonDriver).RunAlert(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, obj.name + ": " + obj.data);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, obj.name + ": " + obj.data);
			//return false;
		}catch(NoAlertPresentException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, obj.name + ": " + obj.data);
			//return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, obj.name + ": " + obj.data + " " + e.getMessage());
			return false;			
		}catch (UnhandledAlertException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, obj.name + ": " + obj.data + " " + e.getMessage());
			return false;			
		}
		return true;
	}

	protected boolean casePopup(DataElementObj obj) {
		try{
			WebDriver popup = ctrl.popup.RunPopup(obj);
	//		WebDriver popup = new Popup(commonDriver, logCat).RunPopup(obj);
			if (popup != null) {
				sendToLogCustom(logexestatus.PASS, logaction.Popup, obj.name + ": " + obj.data);
			} else {
				sendToLogCustom(logexestatus.FAIL, logaction.Popup, obj.name + ": " + obj.data);
				return false;
			}
		}catch (UnhandledAlertException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Popup, obj.name + ": " + obj.data  + " ' -UnhandledAlertException");
			return false;	
		}catch (UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Popup, obj.name + ": " + obj.data  + " ' KeywordsCOM -UnreachableBrowserException");
			return false;	
		}
		return true;
	}

	protected boolean caseDate(DataElementObj obj) {
		try{
			ctrl.datePicker.runDatePicker(obj);
//			new DatePicker(commonDriver).runDatePicker(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Date, obj.name + ": " + obj.data);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Date, obj.name + ": " + obj.data);
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Date, obj.name + ": " + obj.data + " " + e.getMessage());
			return false;			
		}
		return true;
	}

	protected boolean caseCheckbox(DataElementObj obj) {
		try{
			ctrl.checkBox.RunCheckBox(obj);
//			new CheckBox(commonDriver).RunCheckBox(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Checkbox, obj.name + ": " + obj.data);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Checkbox, obj.name + ": " + obj.data + " -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Checkbox, obj.name + ": " + obj.data + " -InvalidElementStateException");
			return false;			
		}
		return true;
	}

	protected boolean caseRadio(DataElementObj obj) {
		try{
			ctrl.radio.RunRadio(obj);
//			new Radio(commonDriver).RunRadio(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Radio, obj.name + ": " + obj.data);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Radio, obj.name + ": " + obj.data + " -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Radio, obj.name + ": " + obj.data + " -InvalidElementStateException");
			return false;			
		}catch(ArrayIndexOutOfBoundsException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Radio, obj.name + ": " + obj.data + " -ArrayIndexOutOfBoundsException");
			return false;		
		}catch(IndexOutOfBoundsException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Radio, obj.name + ": " + obj.data + " -IndexOutOfBoundsException");
			return false;		
		}
		return true;
	}
	
//	Default case
//	protected boolean caseText(DataElementObj obj) {
//		try{
//			new Type().RunText(obj);
//			sendToLogCustom(logexestatus.PASS, logaction.Text, obj.name + ": " + obj.data);
//		}catch (TimeoutException e){
//			sendToLogCustom(logexestatus.FAIL, logaction.Text, obj.name + ": " + obj.data);
//			return false;
//		}
//		return true;
//	}
	
	protected boolean caseText(DataElementObj obj) {
		//@Override Method for GENERATE NUMBER
		try{
			obj.data = obj.fieldValue!="" && obj.fieldValue!="null" ? getNum((int)Math.round(Double.parseDouble(obj.fieldValue))) : obj.data;
			ctrl.type.RunText(obj);
//			new Type(commonDriver).RunText(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Text, obj.name + ": " + obj.data);
		}catch(NumberFormatException e){
			ctrl.type.RunText(obj);
//			new Type(commonDriver).RunText(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Text, obj.name + ": " + obj.data);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Text, obj.name + ": " + obj.data + " -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Text, obj.name + ": " + obj.data + " -InvalidElementStateException");
			return false;			
		}catch (UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Text, obj.name + ": " + obj.data + " -UnreachableBrowserException");
			return false;			
		}
		return true;
	}
	
	protected String getNum(int point){
		  Random ran = new Random();
		  int low = (int) Math.pow(10, point-1);
		  int high = (int) Math.pow(10, point)-low;
		  int tmp = ran.nextInt(high) + low;
		  return ""+tmp;
	}
	
	protected boolean caseDropdown(DataElementObj obj) {
		try{
				ctrl.dropdown.RunDropdown(obj, false);
//				new Dropdown(commonDriver).RunDropdown(obj, false);
				sendToLogCustom(logexestatus.PASS, logaction.Dropdown, obj.name + ": " + obj.data + ": " + obj.fieldValue);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, obj.name + ": " + obj.data + ": " + obj.fieldValue + " -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, obj.name + ": " + obj.data + ": " + obj.fieldValue + " -InvalidElementStateException");
			return false;			
		}catch(NoSuchElementException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, obj.name + ": " + obj.data + ": " + obj.fieldValue + " -NoSuchElementException");
			return false;				
		}catch(NullPointerException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, obj.name + ": " + obj.data + ": " + obj.fieldValue + " -NullPointerException");
			return false;				
		}catch(UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, obj.name + ": " + obj.data + ": " + obj.fieldValue + " -UnreachableBrowserException");
			return false;			
		}catch(UnhandledAlertException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, obj.name + ": " + obj.data + ": " + obj.fieldValue + " -UnhandledAlertException");
			return false;					
		}
		return true;
	}

	protected boolean caseButton(DataElementObj obj) {
		try{
			ctrl.button.RunButton(obj);
//			new Button(commonDriver).RunButton(obj);
			sendToLogCustom(logexestatus.PASS, logaction.Click, obj.name + ": " + obj.data);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Click, obj.name + ": " + obj.data);
			if(obj.fieldType == fieldType.linktext)	{
				//Nothing TO DO
			}
			else if(obj.data.toLowerCase().contains("DRAFT")){
				//Nothing TO DO
			}else{
				return false;
			}
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Click, obj.name + ": " + obj.data + " -InvalidElementStateException");
			return false;			
		}catch (NullPointerException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Click, obj.name + ": " + obj.data + " -NullPointerException");
			return false;			
		}catch(UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Click, obj.name + ": " + obj.data + " -UnreachableBrowserException");
			return false;			
		}
		return true;
	}	
	
	protected boolean alert(){
		try{
			ctrl.alertHandle.execute();
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, "Alert!");
		}catch(NoAlertPresentException e){
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, "No alert!");
		}
		return true;
		
	}
	
	protected int getSize() {
		ReadExcelController rdExl = new ReadExcelXLS(workBookPath, workSheetPath);
		
		int size = -1;
		try{
			size = (int) Math.round(Double.parseDouble(rdExl.getString(col.A, 1)));
			sendToLogCustom(logexestatus.PASS, logaction.LoadData, "size: " + size);
		}catch(NumberFormatException e){
			size = -1;		
			sendToLogCustom(logexestatus.FAIL, logaction.LoadData, "size: " + size + " -NumberFormatException");
		}catch(NullPointerException e){
			size = -1;		
			sendToLogCustom(logexestatus.FAIL, logaction.LoadData, "size: " + size + " -NullPointerException");
		}
		rdExl.closeFile();
		return size;
	}
	
	protected void sendToLogStart() {
		sendToLogCustom(logexestatus.START, logaction.None, null);
	}

	protected void sendToLogFinish() {
		sendToLogCustom(logexestatus.FINISH, logaction.None, null);
	}
	
	protected void sendToLogCustom(logexestatus logexestatus, logaction logaction, String str) {
		logCat.sendToLog(logexestatus, logoperation, logtab, logsubtab, logelement.None,
				logaction, str);
	}
}
