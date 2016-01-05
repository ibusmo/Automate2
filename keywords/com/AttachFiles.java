
package com;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class AttachFiles extends KeywordsCOM {
	
	public AttachFiles(Controller ctrl) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.AttachFiles;
		super.logsubtab 		= log.LogTag.logsubtab.None;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		
		try {
			// Click เอกสารแนบ Tab
			String btnAttachFilesTab = "เอกสารแนบ";
//			new Click().auto(fieldType.linktext, btnAttachFilesTab);
			ctrl.button.linkText(btnAttachFilesTab);
			sendToLogCustom(logexestatus.PASS, logaction.Click, "เอกสารแนบ  Tab");			
		} catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "เอกสารแนบ Tab -TimeoutException");
			return false;
		}
		try{
			// Alert
//			new AlertHandle().execute();
			ctrl.alertHandle.execute();
			sendToLogCustom(logexestatus.PASS, logaction.Comfirm, "เอกสารแนบ Tab OK");
		}catch (NullPointerException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Comfirm, "เอกสารแนบ Tab -NullPointerException");
			return false;
		}
		int numRows=0;
		try{
			//Get number of Table Rows
			String elementOfTableX = "//*[@id='divListDocument']/div[2]/div/table/tbody/tr";
			//String elementOfTable = "#divListDocument > div:nth-child(4) > div > table > tbody > tr";
			ctrl.waitFor.xpath(elementOfTableX);
//			new WaitFor().xpath(elementOfTableX);
			numRows = ctrl.driver.findElements(By.xpath(elementOfTableX)).size();
			sendToLogCustom(logexestatus.PASS, logaction.Check, "There are " +(numRows-1)+ " " + " rows.");
		}catch(TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Check, "Can't get number of rows. -TimeoutException");
			return false;
		}
		for(int index=2;index<=numRows;index++){
			try{
				String xpathTmp = "//*[@id='divListDocument']/div[2]/div/table/tbody/tr["+index+"]/td[7]/button[2]/img";
									   //*[@id="divListDocument"]/div[2]/div/table/tbody/tr[2]        /td[7]/button[2]/img
				ctrl.waitFor.xpath(xpathTmp);
//				new WaitFor().xpath(xpathTmp);
				String bynTitle = ctrl.driver.findElement(By.xpath(xpathTmp)).getAttribute("src");
				//System.out.println(bynTitle);
				if(bynTitle.toLowerCase().contains("select")){
					ctrl.driver.findElement(By.xpath(xpathTmp)).click();
					//Select File Page
					ctrl.waitFor.name("theFile");
//					new WaitFor().name("theFile");
					ctrl.driver.findElement(By.name("theFile")).sendKeys("C:\\Windows\\system.ini");
					String btnAccept = "//*[@id='docList']/table/tbody/tr[4]/td/button[1]";
					ctrl.driver.findElement(By.xpath(btnAccept)).click();
					sendToLogCustom(logexestatus.PASS, logaction.Attach, "Attached\t" + (index-1));
				}else{
					sendToLogCustom(logexestatus.PASS, logaction.Attach, "Not require\t" + (index-1));						
				}
			}catch(TimeoutException e){
				sendToLogCustom(logexestatus.FAIL, logaction.Attach, "Error, Something went wrong. -TimeoutException");
				return false;
			}
		}
		
		sendToLogFinish();
		return true;
	}
	
}
