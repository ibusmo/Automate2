
package creditapplication;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;
import testdata.CellTag.CATask;

public class VerifyAndCommitmentSendWork extends KeywordsCOM {
	
	CATask cAPath;
	
	public VerifyAndCommitmentSendWork(Controller ctrl, CATask cAPath) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.VerifyCommitment;
		super.logtab 			= log.LogTag.logtab.SendWork;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
		this.cAPath = cAPath;

	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		
		try {
			
			ctrl.button.linkText("ส่งงาน");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ส่งงาน");
			
			alert();

			switch(cAPath){
				case auto:
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = auto");
					break;
				case branch:
					ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 2);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = ส่งงานไปวิเคราะห์ที่สาขา");	
					break;
				case section:
					ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 3);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = ส่งงานไปวิเคราะห์ที่เขต");
					break;
				default:
					sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, "งงกับทางเลือกมาก");
					return false;
			}
			
			ctrl.button.xpath("//*[@id='btnSendDiv']/table[3]/tbody/tr/td/button");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "send ส่งงานต่อ");

			alert();
			
			ctrl.verifyData.urlContains("inboxAction.do");
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "Verify Send ส่งงาน");
			
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
	
}
