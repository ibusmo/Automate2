package contract;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class PrepMakeContractSignDate extends KeywordsCOM {
	
	public PrepMakeContractSignDate(Controller ctrl){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.MakeContract;
		super.logtab 			= log.LogTag.logtab.ContractSignDate;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		try{
			if(enterTab()==false)					return false;			
			if(listAllLoanContract()==false)		return false;
			if(listAllCollContract()==false)		return false;
			
		}catch(TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractAdditional -TimeoutException");
		}catch(NoSuchElementException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractAdditional -NoSuchElementException");
		}
		sendToLogFinish();
		return true;
	}

	private boolean listAllLoanContract() {
		String elementRowTr = "//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr";						
		ctrl.waitFor.xpath(elementRowTr);
		int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
		System.out.println(numberOfRows-2);
		boolean flag = true;
		for(int idx=2; idx<numberOfRows; idx++){
			String loanType = ctrl.verifyData.getTextByXpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[2]");
			System.out.println(loanType);
			if(!flag)	return false;
			if(loanType.contains("เงินกู้ที่มีกำหนดระยะเวลา")){
				flag = COMLTL(idx);
			}else if(loanType.contains("ตั๋วสัญญาใช้เงิน")){
				flag = COMPN(idx);
			}else if(loanType.contains("หนังสือค้ำประกัน")){
				flag = COMBG(idx);
			}else if(loanType.contains("บัญชีกระแสรายวัน")){
				flag = DDAOD(idx);
			}
		}
		return flag;
	}
	
	private boolean COMLTL(int idx) {
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "เงินกู้ที่มีกำหนดระยะเวลา");
		//วันที่เซ็นสัญญา
		ctrl.button.linkText("วันที่เซ็นสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "วันที่เซ็นสัญญา");
		ctrl.datePicker.nameSetDate("signDate", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, ctrl.datePicker.getCurDate());
		ctrl.datePicker.nameSetDate("disbursementDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, ctrl.datePicker.getCurDate());
		ctrl.button.xpath("//*[@id='tabs-2']/div/div[8]/button");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "คำนวณ");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}	
	
	private boolean COMPN(int idx) {
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "ตั๋วสัญญาใช้เงิน");
		//วันที่เซ็นสัญญา
		ctrl.button.linkText("วันที่เซ็นสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "วันที่เซ็นสัญญา");
		ctrl.datePicker.nameSetDate("signDate", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่เซ็นสัญญา : " + ctrl.datePicker.getCurDate());
		ctrl.datePicker.nameSetDate("disbursementDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ประมาณการเบิกจ่าย : " + ctrl.datePicker.getCurDate());
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean COMBG(int idx) {
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "หนังสือค้ำประกัน");
		//หนังสือค้ำประกัน
		ctrl.button.linkText("วันที่เซ็นสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "วันที่เซ็นสัญญา");
		ctrl.datePicker.nameSetDate("signDate", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่เซ็นสัญญา : " + ctrl.datePicker.getCurDate());
		ctrl.datePicker.nameSetDate("disbursementDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ประมาณการเบิกจ่าย : " + ctrl.datePicker.getCurDate());
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean DDAOD(int idx) {
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "บัญชีกระแสรายวัน");
		//วันที่เซ็นสัญญา
		ctrl.button.linkText("วันที่เซ็นสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "วันที่เซ็นสัญญา");
		ctrl.datePicker.nameSetDate("signDate", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่เซ็นสัญญา : " + ctrl.datePicker.getCurDate());
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean listAllCollContract() {
		String elementRowTr = "//*[@id='tabs-1']/div[2]/div/table/tbody/tr";						
		ctrl.waitFor.xpath(elementRowTr);
		int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
		System.out.println(numberOfRows-2);
		boolean flag = true;
		for(int idx=2; idx<numberOfRows; idx++){
			String loanType = ctrl.verifyData.getTextByXpath("//*[@id='tabs-1']/div[2]/div/table/tbody/tr["+idx+"]/td[3]");
			System.out.println(loanType);
			if(!flag)	return false;
			if(loanType.contains("บุคคล/นิติบุคคล ค้ำประกัน")){
				flag = warranter(idx);
			}
		}
		return flag;
	}
	
	private boolean warranter(int idx) {
		String str = "//*[@id='tabs-1']/div[2]/div/table/tbody/tr["+idx+"]/td[1]/input";
//		System.out.println(str);
		ctrl.button.xpath(str);
		sendToLogCustom(logexestatus.PASS, logaction.Click, "บุคคล/นิติบุคคล ค้ำประกัน");
		//วันที่เซ็นสัญญา
		ctrl.button.linkText("วันที่เซ็นสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "วันที่เซ็นสัญญา");
		ctrl.datePicker.nameSetDate("signDate", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่เซ็นสัญญา : " + ctrl.datePicker.getCurDate());
		ctrl.checkBox.name("checkCopyFlg");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean enterTab(){
//		if(ctrl.verifyData.textContainsByXpath("//*[@id='content']/div/div/div[1]/span[1]", "จัดทำสัญญาสินเชื่อและสัญญาหลักประกัน")==false){
//			sendToLogCustom(logexestatus.FAIL, logaction.Verify, "จัดทำสัญญาสินเชื่อและสัญญาหลักประกัน");
//			return false;
//		}
		sendToLogCustom(logexestatus.PASS, logaction.Verify, "จัดทำสัญญาสินเชื่อและสัญญาหลักประกัน");
		
		ctrl.button.linkText("วันที่เซ็นสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab วันที่เซ็นสัญญา");
		
		alert();
		
		return true;
	}
	
}
