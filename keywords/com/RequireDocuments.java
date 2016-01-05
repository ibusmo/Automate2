
package com;

import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class RequireDocuments extends KeywordsCOM {
	
	public RequireDocuments(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.RequireDoc;
		super.logtab 			= log.LogTag.logtab.None;
		super.logsubtab 		= log.LogTag.logsubtab.None;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		
		try {
			ctrl.button.linkText("ใบตรวจสอบเอกสาร");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "ใบตรวจสอบเอกสาร Tab");
			ctrl.alertHandle.execute();
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, "ใบตรวจสอบเอกสาร Tab -Confirm");
			ctrl.button.id("btnSave");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "save บันทึก");
		} catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Documents, " -TimeoutException");	
			return false;		
		}catch (NullPointerException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Documents, " -NullPointerException");		
			return false;			
		}
		
		sendToLogFinish();
		return true;
	}
	
}
