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

public class SecSBROToCMDeptAssign extends KeywordsCOM {

	private String CMDeptName;
	private String SBROSec;
	
	public SecSBROToCMDeptAssign(Controller ctrl, String CMDeptName, String SBROSec){
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.SBROAssign;
		super.logtab 			= log.LogTag.logtab.Assignment;
		super.logsubtab 		= log.LogTag.logsubtab.None;	

		this.CMDeptName = CMDeptName;
		this.SBROSec = SBROSec;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		
		try {
			// Click Send ส่งงานต่อ
			if(ctrl.verifyData.textContainsByXpath("//*[@id='content']/div/div/div[1]", "เลือกส่งงานต่อ")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "เลือกส่งงานต่อ");
				return false;
			}
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "เลือกส่งงานต่อ");
			
			ctrl.button.linkText("ส่งงาน");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ส่งงาน");

			alert();
			
			//เลือกส่งงานต่อ
			ctrl.dropdown.robotByXpath("//*[@id='btnSendDiv']/table/tbody/tr[1]/td/div[2]/input", 2);
			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก = เลือกส่งงานต่อ");
			
			// Select DropDown :หน่วยงาน * = เขตวัชรพล
//			ctrl.dropdown.robotByXpath("//*[@id='assignUser']/div/div[3]/input", SBROSecIndex);
//			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":หน่วยงาน * = เขตวัชรพล");
			
			//A bug is here
			ctrl.dropdown.robotSelectId("searchDept1", 2);
			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":หน่วยงาน/สาขา = 0000 : หน่วยงานขึ้นตรงเขต/กอง");
			ctrl.button.xpath("//*[@id='div-NEWSM2SBROSec']/div/table/tbody/tr[6]/td/button[1]");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "ค้นหา");			
			ctrl.waitFor.name("searchBOList[1].listResultBO[0].select");
			
			//HACK THE WORLD
			String hackAllHidden = "$(\"input[type*='hidden']\").prop('type', 'text');";
			ctrl.jsExecute.forceExe(hackAllHidden);
	
			//Search user and select
			int userIndex=2;
			String elementRowTr = "//*[@id='div-NEWSM2CMDept']/table[2]/tbody/tr";						
			ctrl.waitFor.xpath(elementRowTr);
			int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
			for(int idx=2; idx<=numberOfRows; idx++){
				String tempUserText = ctrl.verifyData.getTextByXpath("//*[@id='div-NEWSM2CMDept']/table[2]/tbody/tr["+idx+"]/td[2]");
				String tempUserValue = ctrl.verifyData.getValueByXpath("//*[@id='div-NEWSM2CMDept']/table[2]/tbody/tr["+idx+"]/input[1]");
				System.out.println("'"+tempUserText+"' - '"+tempUserValue);
				if(tempUserValue.contains(CMDeptName)){
					userIndex = idx;
					break;
				}
			}
			
			ctrl.checkBox.xpath("//*[@id='div-NEWSM2CMDept']/table[2]/tbody/tr["+userIndex+"]/td[1]/input");			
			sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "มอบหมายงาน :SBRO to CMDept " + userIndex + "-" + CMDeptName );
			
			//Search user and select
			userIndex=2;
			elementRowTr = "//*[@id='div-NEWSM2SBROSec']/table[2]/tbody/tr";						
			ctrl.waitFor.xpath(elementRowTr);
			numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
			for(int idx=2; idx<=numberOfRows; idx++){
				String tempUserText = ctrl.verifyData.getTextByXpath("//*[@id='div-NEWSM2SBROSec']/table[2]/tbody/tr["+idx+"]/td[2]");
				String tempUserValue = ctrl.verifyData.getValueByXpath("//*[@id='div-NEWSM2SBROSec']/table[2]/tbody/tr["+idx+"]/input[1]");
				System.out.println("'"+tempUserText+"' - '"+tempUserValue);
				if(tempUserValue.contains(SBROSec)){
					userIndex = idx;
					break;
				}
			}
			
			ctrl.checkBox.xpath("//*[@id='div-NEWSM2SBROSec']/table[2]/tbody/tr["+userIndex+"]/td[1]/input");			
			sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "มอบหมายงาน :SBRO to SBROSec " + userIndex + "-" + SBROSec );
			
			ctrl.button.xpath("//*[@id='completeTaskActionForm']/div[3]/table/tbody/tr/td/button[2]");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Send ส่งงานต่อ");

			alert();

			if(ctrl.verifyData.urlContains("inboxAction.do")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "Verify Send ส่งงาน");
				return false;
			}
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "Verify Send ส่งงาน");
			
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -TimeoutException -" + CMDeptName);
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
