
package creditanalysis;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;
import testdata.CellTag.CreditTask;

public class BranchCACommentSendWork extends KeywordsCOM {
	
	private CreditTask creditPath;
	
	public BranchCACommentSendWork(Controller ctrl, CreditTask creditPath) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.SendWork;
		super.logtab 			= log.LogTag.logtab.SendWork;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
		this.creditPath = creditPath;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		
		try {
			
			ctrl.button.linkText("ส่งงาน");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ส่งงาน");

			alert();

//			ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 2);
//			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "send ส่งงานต่อ");
			
			String defaultPath = ctrl.verifyData.getValueByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input");
			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, "********************** :defaultPath iLog - " + defaultPath);
			
			ctrl.screenCapture.saveShotImage(ctrl.pathVariable.getRelativeLog() + "_BranchApproval_pre" + ".jpg");
			
			switch(creditPath){
				case RCOM:
					ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 2);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = ส่งงานไปวิเคราะห์ที่เขต RCOM");
					break;
				case KSCCOM:
					ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 3);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = ส่งงานไปวิเคราะห์ที่กสช. KSCCOM");
					break;
				case BCOM:
					ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 4);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = ส่งงานไปวิเคราะห์ที่สาขา BCOM");	
					break;
					
				case autoRCOM:
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = Auto RCOM");
					break;
				case autoKSCCOM:
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = Auto KSCCOM");
					break;
				case autoBCOM:
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = Auto BCOM");	
					break;
					
				default:
					sendToLogCustom(logexestatus.FAIL, logaction.Dropdown, "งงกับทางเลือกมาก");
					break;
			}

			ctrl.screenCapture.saveShotImage(ctrl.pathVariable.getRelativeLog() + "_BranchApproval_pos" + ".jpg");
			
			ctrl.button.xpath("//*[@id='btnSendDiv']/table[3]/tbody/tr/td/button");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "send ส่งงานต่อ");

			alert();
			
			if(ctrl.verifyData.urlContains("inboxAction.do")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "Verify Send ส่งงาน");
				return false;
			}
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
