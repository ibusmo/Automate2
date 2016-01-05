package creditanalysis;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class SecCMDeptToCMAutoAssign extends KeywordsCOM {
	
	String CMName;

	public SecCMDeptToCMAutoAssign(Controller ctrl, String CMName){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.CMDEPTAssign;
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
			
			if(CMName.toLowerCase().matches("rr")){
				ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table/tbody/tr[1]/td/div[2]/input", 2);
				sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก =ส่งงานต่อ (Auto Assign)");
				
				ctrl.button.id("btnSendNoAssignee");
				sendToLogCustom(logexestatus.PASS, logaction.Click, "send ส่งงานต่อ  (Auto Assign)");
			}else{
				ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table/tbody/tr[1]/td/div[2]/input", 3);
				sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก =ส่งงานต่อ (Manual Assign)");
				
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
				sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "มอบหมายงาน :CMDept to CM " + userIndex + "-" + CMName );
				
				ctrl.button.id("btnSend");
				sendToLogCustom(logexestatus.PASS, logaction.Click, "send ส่งงานต่อ  (Manual Assign)");
			}

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
