package contract;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class LoanAgreePrepareAccept extends KeywordsCOM {

	public LoanAgreePrepareAccept(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.PrepareAccept;
		super.logtab 			= log.LogTag.logtab.PrepareAccept;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		
		try {

			if(fee()==false)						return false;
			if(contractCheck()==false)				return false;
			if(insurncePremium()==false)			return false;
			if(sendwork()==false)					return false;
			
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -TimeoutException");
			return false;
		}catch (NoSuchElementException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -NoSuchElementException");
			return false;
		}catch (NullPointerException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -NullPointerException");
			return false;
		}catch(NoAlertPresentException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -NoAlertPresentException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -InvalidElementStateException");
			return false;			
		}
		
		sendToLogFinish();
		return true;
	}

	private boolean insurncePremium() {		
		ctrl.button.linkText("เบี้ยประกัน");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab เบี้ยประกัน");
		alert();
		ctrl.radio.name("requireInsureFlag", 2);
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "Tab ไม่มีการทำประกันภัย");
		ctrl.button.id("btnSave");	
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");		
		return true;
	}

	private boolean fee() {
		ctrl.button.linkText("ค่าธรรมเนียม");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ค่าธรรมเนียม");
		alert();
		ctrl.radio.name("feeForm.requireFeeFlag", 2);
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "Tab ไม่มีการเรียกเก็บค่าธรรมเนียมจากลูกค้า");
		ctrl.button.id("btnSave");	
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");		
		return true;
	}
	
	private boolean contractCheck() {
		ctrl.button.linkText("ความสมบูรณ์ของสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ความสมบูรณ์ของสัญญา");
		alert();
		ctrl.radio.name("contractCompleteFlag", 1);
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "Tab สัญญาลงนามเรียบร้อย");
		ctrl.button.id("btnSave");	
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");		
		return true;
	}

	private boolean sendwork() {
		ctrl.button.linkText("ส่งงาน");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ส่งงาน");
		
		alert();
		
		ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 2);
		sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "send ส่งงานต่อ");
		
		ctrl.button.xpath("//*[@id='btnSendDiv']/table[3]/tbody/tr/td/button");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "send ส่งงานต่อ");
		
		alert();
		
		if(ctrl.verifyData.urlContains("inboxAction.do")==false){
			sendToLogCustom(logexestatus.FAIL, logaction.Verify, "Verify Send ส่งงาน");
			return false;
		}
		sendToLogCustom(logexestatus.PASS, logaction.Verify, "Verify Send ส่งงาน");
		return true;
	}
	
	
	
}
