package contract;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class PrepMakeContractAdditional extends KeywordsCOM {
	
	public PrepMakeContractAdditional(Controller ctrl){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.MakeContract;
		super.logtab 			= log.LogTag.logtab.ContractAdditional;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		try{
			if(enterTab()==false)					return false;			
			if(listAllLoanContract()==false)		return false;
			if(listAllCollateralContract()==false)	return false;
			
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
		//NT1.1
		ctrl.button.linkText("NT1.1");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "NT1.1");
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());
		ctrl.datePicker.nameSetDate("loanContractDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "สัญญาเงินกู้ลงวันที่: " + ctrl.datePicker.getCurDate());
		ctrl.type.name("witness1", "นายพยาน คนที่1");
		sendToLogCustom(logexestatus.PASS, logaction.Text, "นายพยาน คนที่1");
		ctrl.type.name("witness2", "นางพยาน คนที่2");
		sendToLogCustom(logexestatus.PASS, logaction.Text, "นายพยาน คนที่2");
		ctrl.checkBox.name("copyToOtherContractFlag");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean COMPN(int idx) {
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "ตั๋วสัญญาใช้เงิน");
		//10530000
		ctrl.button.linkText("10530000");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "10530000");
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());
		ctrl.checkBox.name("copyToOtherContractFlag");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean COMBG(int idx) {
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "หนังสือค้ำประกัน");
		//AT010005
		ctrl.button.linkText("AT010005");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "AT010005");	
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());
		ctrl.datePicker.nameSetDate("loanContractDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "สัญญาเงินกู้ลงวันที่: " + ctrl.datePicker.getCurDate());
		ctrl.checkBox.name("copyToOtherContractFlag");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");		
		return true;
	}
	
	private boolean DDAOD(int idx) {
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Radio, "บ้ญชีกระแสรายวัน");
		//10210000
		ctrl.button.linkText("10210000");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "10210000");
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());
		ctrl.checkBox.name("copyToOtherContractFlag");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}

	private boolean listAllCollateralContract() {
		String elementRowTr = "//*[@id='tabs-1']/div[1]/div[2]/div/table/tbody/tr";						
		ctrl.waitFor.xpath(elementRowTr);
		int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
		System.out.println(numberOfRows-2);
		if(numberOfRows-2<=0)
			return true;
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[2]/div/table/tbody/tr[2]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Switch Mode");
		boolean flag = true;
		for(int idx=2; idx<numberOfRows; idx++){
			String collType = ctrl.verifyData.getTextByXpath("//*[@id='collateralContractActionForm']/div[4]/div/table/tbody/tr["+idx+"]/td[3]");
			System.out.println(collType);
			if(!flag)	return false;
			if(collType.contains("ที่ดินพร้อมสิ่งปลูกสร้าง")){
				flag = landAndBuilding(idx);
			}else if(collType.contains("อาคารสิ่งปลูกสร้าง")){
				flag = building(idx);
			}else if(collType.contains("ที่ดิน")){
				flag = land(idx);
			}else if(collType.contains("บุคคล/นิติบุคคล ค้ำประกัน")){
				flag = warranter(idx);
			}
		}
		return true;
	}
	
	private boolean landAndBuilding(int idx) {
//		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[2]/div/table/tbody/tr["+idx+"]/td[1]/input");
		ctrl.button.xpath("//*[@id='collateralContractActionForm']/div[4]/div/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "ที่ดินพร้อมสิ่งปลูกสร้าง");
		//20590000
		ctrl.button.linkText("20590000");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "20590000");
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());
		ctrl.checkBox.name("copyToOtherContractFlag");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean building(int idx) {
		ctrl.button.xpath("//*[@id='collateralContractActionForm']/div[4]/div/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "อาคารสิ่งปลูกสร้าง");
		//NT17
		ctrl.button.linkText("NT17");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "NT17");
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());
		ctrl.checkBox.name("copyToOtherContractFlag");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}
	
	private boolean land(int idx) {
		ctrl.button.xpath("//*[@id='collateralContractActionForm']/div[4]/div/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "ที่ดิน");
		//20590000
		ctrl.button.linkText("20590000");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "20590000");
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());
		ctrl.checkBox.name("copyToOtherContractFlag");
		sendToLogCustom(logexestatus.PASS, logaction.Checkbox, "คัดลอกไปยังเอกสารที่เกี่ยวข้อง");
		ctrl.button.id("btnSave");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Save");
		return true;
	}	
	
	private boolean warranter(int idx) {
		ctrl.button.xpath("//*[@id='collateralContractActionForm']/div[4]/div/table/tbody/tr["+idx+"]/td[1]/input");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "บุคคล/นิติบุคคล ค้ำประกัน");
		//20270000
		ctrl.button.linkText("20270000");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "20270000");
		ctrl.datePicker.nameSetDate("signDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "วันที่ลงนามในสัญญา: " + ctrl.datePicker.getCurDate());  
		ctrl.datePicker.nameSetDate("loanContractDt", ctrl.datePicker.getCurDate());
		sendToLogCustom(logexestatus.PASS, logaction.Date, "สัญญาเงินกู้ลงวันที่: " + ctrl.datePicker.getCurDate()); 
		ctrl.checkBox.name("copyToOtherContractFlag");
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
		
		ctrl.button.linkText("ข้อมูลเพิ่มเติมสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ข้อมูลเพิ่มเติมสัญญา");
		
		alert();
		
		return true;
	}
	
}
