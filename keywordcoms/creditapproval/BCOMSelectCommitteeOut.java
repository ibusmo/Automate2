package creditapproval;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;
import testdata.CellTag.ApprSystem;

public class BCOMSelectCommitteeOut extends KeywordsCOM{

	private String[] BCOM;
	private ApprSystem creditSystem;
	
	public BCOMSelectCommitteeOut(Controller ctrl, ApprSystem creditSystem, String[] BCOM){
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.BCOM;
		super.logtab 			= log.LogTag.logtab.SelectCommittee;
		super.logsubtab 		= log.LogTag.logsubtab.None;	

		this.BCOM = BCOM;
		this.creditSystem = creditSystem;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		
		try {			
			ctrl.button.linkText("ส่งงาน");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ส่งงาน");
			alert();
			
			switch(creditSystem){
				case IN:
					ctrl.dropdown.robotSelectName("selectedResponse", 2);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก * = อนุมัติในระบบ");
					break;
				case OUT:
					ctrl.dropdown.robotSelectName("selectedResponse", 3);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":กรุณาเลือกทางเลือก * = อนุมัตินอกระบบ");
					break;
			}
			ctrl.button.xpath("//*[@id='btnSendDiv']/table[3]/tbody/tr/td/button");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Send ส่งงานต่อ");
			alert();
			
			switch(creditSystem){
			case OUT:
			case IN:
					ctrl.dropdown.robotSelectName("ouGroupCode", 2);
					sendToLogCustom(logexestatus.PASS, logaction.Dropdown, ":หน่วยงาน * = 101010115300430 - ธนาคารออมสินสาขาศูนย์ราชการเฉลิมพระเกียรติ แจ้งวัฒ");
					
					//Search user and select
					int userIndex=2;
					String elementRowTr = "//*[@id='BCOMTable']/tr";						
					ctrl.waitFor.xpath(elementRowTr);
					int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size()-1;
					
					for(int idx=2; idx<=numberOfRows; idx++){
						String tempUserText = ctrl.verifyData.getTextByXpath("//*[@id='BCOMTable']/tr["+idx+"]/td[2]");
						String tempUserValue = ctrl.verifyData.getValueByXpath("//*[@id='BCOMTable']/tr["+idx+"]/td[1]/input");
							System.out.println("'"+tempUserText+"' - '"+tempUserValue);
						if(isInChecklist(tempUserValue)){
							userIndex = idx;
							
							ctrl.checkBox.xpath("//*[@id='BCOMTable']/tr["+userIndex+"]/td[1]/input");			
							sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "เลือกคณะกรรมการ :CMDept/SBRO to BCOM " + userIndex + "-" + tempUserText );						
						}
					}
					
					ctrl.button.xpath("//*[@id='assignUser']/table/tbody/tr/td/button[2]");
					sendToLogCustom(logexestatus.PASS, logaction.Click, "Send ส่งงานต่อ");
					alert();
					break;
					
//				case OUT:
//					
//					break;
			}

			if(ctrl.verifyData.urlContains("inboxAction.do")==false){
				sendToLogCustom(logexestatus.FAIL, logaction.Verify, "Verify Send ส่งงาน");
				return false;
			}
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "Verify Send ส่งงาน");
			
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "เลือกคณะกรรมการ -TimeoutException -");
			return false;
		}catch (NoSuchElementException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "เลือกคณะกรรมการ -NoSuchElementException");
			return false;
		}catch (NullPointerException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "เลือกคณะกรรมการ -NullPointerException");
			return false;
		}catch(NoAlertPresentException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "เลือกคณะกรรมการ -NoAlertPresentException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "เลือกคณะกรรมการ -InvalidElementStateException");
			return false;			
		}
		
		sendToLogFinish();
		return true;
	}
	
	private boolean isInChecklist(String tmpRCOMName){
		for(String name : BCOM){
			if(tmpRCOMName.contains(name)){
				System.out.println(name);
				return true;
			}
		}
		return false;
	}
}
