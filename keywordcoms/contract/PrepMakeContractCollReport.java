package contract;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class PrepMakeContractCollReport extends KeywordsCOM {

	public PrepMakeContractCollReport(Controller ctrl){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.MakeContract;
		super.logtab 			= log.LogTag.logtab.ContractCollReport;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		try{
			
			ctrl.button.linkText("ชุดสัญญาหลักประกัน");	
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ชุดสัญญาหลักประกัน");
			alert();	
			ctrl.button.id("btnSave");	
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
			
		}catch(TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractCollReport -TimeoutException");
		}catch(NoSuchElementException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractCollReport -NoSuchElementException");
		}
		sendToLogFinish();
		return true;
	}
	
}
