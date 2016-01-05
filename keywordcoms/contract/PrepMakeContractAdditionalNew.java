package contract;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class PrepMakeContractAdditionalNew extends KeywordsCOM {
	
	public PrepMakeContractAdditionalNew(Controller ctrl){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.MakeContract;
		super.logtab 			= log.LogTag.logtab.ContractAdditionalNew;
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
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractAdditionalNew -TimeoutException");
		}catch(NoSuchElementException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "ContractAdditionalNew -NoSuchElementException");
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
			String loanType = ctrl.verifyData.getTextByXpath("//*[@id='tabs-1']/div[1]/div[1]/table/tbody/tr["+idx+"]/td[1]");
			System.out.println(loanType);
			if(!flag)	return false;
			if(loanType.toLowerCase().contains("com")){
				flag = COM(idx);
			}else if(loanType.toLowerCase().contains("dda")){
				flag = DDA(idx);
			}
		}
		return flag;
	}
	
	private boolean COM(int idx) {
		// TODO Auto-generated method stub
		return true;
	}
	
	private boolean DDA(int idx) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean listAllCollateralContract() {
		String elementRowTr = "//*[@id='tabs-1']/div[1]/div[2]/div/table/tbody/tr";						
		ctrl.waitFor.xpath(elementRowTr);
		int numberOfRows = ctrl.driver.findElements(By.xpath(elementRowTr)).size();
		System.out.println(numberOfRows-2);
		boolean flag = true;
		for(int idx=2; idx<numberOfRows; idx++){
			String collType = ctrl.verifyData.getTextByXpath("//*[@id='tabs-1']/div[1]/div[2]/div/table/tbody/tr["+idx+"]/td[3]");
			System.out.println(collType);
			if(!flag)	return false;
			if(collType.contains("สิ่งปลูกสร้าง")){
				
			}else if(collType.contains("ที่ดิน")){
				
			}
		}
		return true;
	}
	
	private boolean enterTab(){
		if(ctrl.verifyData.textContainsByXpath("//*[@id='content']/div/div/div[1]/span[1]", "จัดทำสัญญาสินเชื่อและสัญญาหลักประกัน")==false){
			sendToLogCustom(logexestatus.FAIL, logaction.Verify, "จัดทำสัญญาสินเชื่อและสัญญาหลักประกัน");
			return false;
		}
		sendToLogCustom(logexestatus.PASS, logaction.Verify, "จัดทำสัญญาสินเชื่อและสัญญาหลักประกัน");
		
		ctrl.button.linkText("[New] ข้อมูลเพิ่มเติมสัญญา");
		sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab [New] ข้อมูลเพิ่มเติมสัญญา");
		
		alert();
		
		return true;
	}
	
}
