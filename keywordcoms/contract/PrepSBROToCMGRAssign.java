package contract;

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

public class PrepSBROToCMGRAssign extends KeywordsCOM {

	private String CMGRName;
	
	public PrepSBROToCMGRAssign(Controller ctrl, String CMGRName){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.SBROAssign;
		super.logtab 			= log.LogTag.logtab.Assignment;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
		this.CMGRName = CMGRName;
	}

	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();

		try {

			if(ctrl.verifyData.textContainsByXpath("//*[@id='content']/div/div/div[1]/span[1]", "เลือกส่งงานต่อ")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "เลือกส่งงานต่อ");
				return false;
			}
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "เลือกส่งงานต่อ");
			
			ctrl.button.linkText("ส่งงาน");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ส่งงาน");
			
			alert();
			
			// Select DropDown :หน่วยงาน * = เขตวัชรพล
			ctrl.dropdown.robotByXpath("//*[@id='assignUser']/div/div[3]/input", 2);
			sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":101010115385090 - หน่วยประเมินราคาและพิธีการสินเชื่อ(ธนาคารออมสินเขตวัชรพล)");
			
			//Search user and select
			int userIndex=2;
			String elementRowTr = "//*[@id='NEWSM2CMGRTable']/tr";						
			ctrl.waitFor.xpath(elementRowTr);
			int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
			for(int idx=2; idx<=numberOfRows; idx++){
				String tempUserText = ctrl.verifyData.getTextByXpath("//*[@id='NEWSM2CMGRTable']/tr["+idx+"]/td[2]");
				String tempUserValue = ctrl.verifyData.getValueByXpath("//*[@id='NEWSM2CMGRTable']/tr["+idx+"]/td[1]/input");
				System.out.println("'"+tempUserText+"' - '"+tempUserValue);
				if(tempUserValue.contains(CMGRName)){
					userIndex = idx;
					break;
				}
			}
			ctrl.checkBox.xpath("//*[@id='NEWSM2CMGRTable']/tr["+userIndex+"]/td[1]/input");			
			sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "มอบหมายงาน :SBRO to SBROSec " + userIndex + "-" + CMGRName );

			ctrl.button.xpath("//*[@id='assignUser']/table/tbody/tr/td/button");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Send ส่งงานต่อ");
			
			alert();

			if(ctrl.verifyData.urlContains("inboxAction.do")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "Verify Send ส่งงาน");
				return false;
			}
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "Verify Send ส่งงาน");
			
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ส่งงาน -TimeoutException -" + CMGRName);
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
