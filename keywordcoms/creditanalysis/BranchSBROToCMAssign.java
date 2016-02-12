package creditanalysis;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class BranchSBROToCMAssign extends KeywordsCOM {

	private String CMName;
	
	public BranchSBROToCMAssign(Controller ctrl, String CMName){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.SBROAssign;
		super.logtab 			= log.LogTag.logtab.Assignment;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
		this.CMName = CMName;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();

		try {

			if(ctrl.verifyData.textContainsByXpath("//*[@id='content']/div/div/div[1]", "มอบหมายงาน")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "มอบหมายงาน");
				return false;
			}
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "มอบหมายงาน");
			
			ctrl.button.linkText("ส่งงาน");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ส่งงาน");
			
			alert();
			
			//เลือกส่งงานต่อ
			ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table/tbody/tr[1]/td/div[2]/input", 2);
			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = เลือกส่งงานต่อ");
			
//			ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table[2]/tbody/tr[1]/td/div[2]/input", 2);
//			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก =ส่งงานต่อ");
//			
//			ctrl.button.xpath("//*[@id='btnSendDiv']/table[3]/tbody/tr/td/button");
//			sendToLogCustom(logexestatus.PASS, logaction.Click, "send ส่งงานต่อ");
//
//			alert();
//			
//			// Select DropDown :หน่วยงาน * = เขตวัชรพล
//			ctrl.dropdown.robotByXpath("//*[@id='assignUser']/div/div[3]/input", CMDeptIndex);
//			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":หน่วยงาน * = เขตวัชรพล");
			
			//HACK THE WORLD
			String hackAllHidden = "$(\"input[type*='hidden']\").prop('type', 'text');";
			ctrl.jsExecute.forceExe(hackAllHidden);
			
			//Search user and select
			int userIndex=2;
			String elementRowTr = "//*[@id='div-NEWSM2CM']/table[2]/tbody/tr";						
			ctrl.waitFor.xpath(elementRowTr);
			int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
			for(int idx=2; idx<=numberOfRows; idx++){
				String tempUserText = ctrl.verifyData.getTextByXpath("//*[@id='div-NEWSM2CM']/table[2]/tbody/tr["+idx+"]/td[2]");
				String tempUserValue = ctrl.verifyData.getValueByXpath("//*[@id='div-NEWSM2CM']/table[2]/tbody/tr["+idx+"]/input[1]");
				System.out.println("'"+tempUserText+"' - '"+tempUserValue);
				if(tempUserValue.contains(CMName)){
					userIndex = idx;
					break;
				}
			}
			ctrl.checkBox.xpath("//*[@id='div-NEWSM2CM']/table[2]/tbody/tr["+userIndex+"]/td[1]/input");			
			sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "มอบหมายงาน :SBRO to CM " + userIndex + "-" + CMName );

			ctrl.button.xpath("//*[@id='completeTaskActionForm']/div[3]/table/tbody/tr/td/button[2]");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Send ส่งงานต่อ");

			if(ctrl.verifyData.urlContains("inboxAction.do")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "Verify Send ส่งงาน");
				return false;
			}
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "Verify Send ส่งงาน");
			
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -TimeoutException -" + CMName);
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
		}catch(UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -UnreachableBrowserException");
			return false;						
		}
		
		sendToLogFinish();
		return true;
	}

}
