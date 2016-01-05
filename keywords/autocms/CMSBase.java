package autocms;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.TimeoutException;

import log.LogTag.logaction;
import log.LogTag.logexestatus;
import base.KeywordsCOM;
import testdata.DataElementObj;
import testdata.CellTag.fieldType;

public class CMSBase extends KeywordsCOM{
	
	@Override
	protected boolean caseButton(DataElementObj obj) {
		try{
			// Force to save draft
			if(obj.data.toUpperCase().contains("DRAFT")){
				ctrl.jsExecute.forceExe("selectPage('doSaveDraft');");
//				executor.executeScript("selectPage('doSaveDraft')", driver.findElement(By.id("content")));
//				ctrl.button.RunButton(obj);
//				new Click().RunButton(obj);
				sendToLogCustom(logexestatus.PASS, logaction.Draft, obj.name + ": " + obj.data);
			}
			// Force to save
			else if(obj.data.toUpperCase().contains("DONE")){
				ctrl.jsExecute.forceExe("selectPage('doSave');");
//				executor.executeScript("selectPage('doSave')", driver.findElement(By.id("content")));
//				ctrl.button.RunButton(obj);
//				new Click().RunButton(obj);
				sendToLogCustom(logexestatus.PASS, logaction.Save, obj.name + ": " + obj.data);
			}
			// Force to OK
			else if(obj.data.toUpperCase().contains("OK")){
				ctrl.jsExecute.forceExe("selectPage('OK');");
//				executor.executeScript("selectPage('OK')", driver.findElement(By.id("content")));
//				ctrl.button.RunButton(obj);
//				new Click().RunButton(obj);
				sendToLogCustom(logexestatus.PASS, logaction.OK, obj.name + ": " + obj.data);
			}
			// General Button
			else{
				ctrl.button.RunButton(obj);
//				new Click().RunButton(obj);
				sendToLogCustom(logexestatus.PASS, logaction.Click, obj.name + ": " + obj.data);
			}
		}catch (TimeoutException e){
			if(obj.fieldType == fieldType.linktext)	{
				//Nothing TO DO
				sendToLogCustom(logexestatus.FAIL, logaction.Click, obj.name + ": " + obj.data);
			}
			else if(obj.data.toUpperCase().contains("DRAFT")){
				//Nothing TO DO
				sendToLogCustom(logexestatus.FAIL, logaction.Draft, obj.name + ": " + obj.data);
			}
			else if(obj.data.toUpperCase().contains("DONE")){
				//Nothing TO DO
				sendToLogCustom(logexestatus.FAIL, logaction.Save, obj.name + ": " + obj.data);
			}
			else if(obj.data.toUpperCase().contains("OK")){
				//Nothing TO DO
				sendToLogCustom(logexestatus.FAIL, logaction.OK, obj.name + ": " + obj.data);
			}else{
				sendToLogCustom(logexestatus.FAIL, logaction.Click, obj.name + ": " + obj.data);
				return false;
			}
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Click, obj.name + ": " + obj.data + " " + e.getMessage());
			return false;			
		}
		return true;
	}
}
