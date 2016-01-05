package contract;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class PrepMakeContractLoanReport extends KeywordsCOM {

	public PrepMakeContractLoanReport(Controller ctrl){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.MakeContract;
		super.logtab 			= log.LogTag.logtab.ContractLoanReport;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		try{
			
			ctrl.button.linkText("ชุดสัญญาสินเชื่อ");		
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ชุดสัญญาสินเชื่อ");
			alert();
			ctrl.button.id("btnSave");	
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
			
		}catch(TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractLoanReport -TimeoutException");
		}catch(NoSuchElementException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractLoanReport -NoSuchElementException");
		}
		sendToLogFinish();
		return true;
	}
	
}
