package com;
import org.openqa.selenium.TimeoutException;
import log.LogTag.logaction;
import log.LogTag.logexestatus;
import base.KeywordsCOM;
import controller.Controller;

public class VerifyAppState2 extends KeywordsCOM {
	
	AppStatusElementObj appStatus;
	
	//viewStatus('038309580126')
	
	public VerifyAppState2(Controller ctrl, String appID){
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.VerifyAppState;
		super.logtab 			= log.LogTag.logtab.VerifyAppState;
		super.logsubtab 		= log.LogTag.logsubtab.None;	

		appStatus = new AppStatusElementObj();
		
		appStatus.appID = appID;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		try {
			// คลิก Tab ค้นหาใบคำขอสินเชื่อ
			ctrl.button.linkText("ค้นหา");
			ctrl.button.linkText("ค้นหาใบคำขอสินเชื่อ");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "Tab ค้นหาใบคำขอสินเชื่อ");
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "Tab ค้นหาใบคำขอสินเชื่อ");
			return false;
		}
		
		try {
			// เลขที่ใบคำขอสินเชื่อ
			ctrl.type.id("searchCaNumber", appStatus.appID);			
			sendToLogCustom(logexestatus.PASS, logaction.Text, "เลขที่ใบคำขอสินเชื่อ : " + appStatus.appID);
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Text, "เลขที่ใบคำขอสินเชื่อ : " + appStatus.appID);
			return false;
		}
		
		try {
			// คลิก ค้นหา
			ctrl.button.xpath("//*[@id='searchApplicationDiv']/div[2]/center/button[1]");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "ค้นหา");
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "ค้นหา");
			return false;
		}
		
		try {
			// คลิก ติดตามสถานะ
			ctrl.button.xpath("//*[@id='searchResultDiv']/table/tbody/tr[2]/td[8]/button[2]");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "ติดตามสถานะ");
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "ติดตามสถานะ");
			return false;
		}
		
		try {
//			String appIdPath 		= "//*[@id='searchResultDiv']/table/tbody/tr[2]/td[1]";
			String usernamePath 	= "//*[@id='searchResultDiv']/table/tbody/tr[2]/td[2]";
			String usernameThaiPath = "//*[@id='searchResultDiv']/table/tbody/tr[2]/td[3]";
			String rolePath 		= "//*[@id='searchResultDiv']/table/tbody/tr[2]/td[4]";
			String analyzePath 		= "//*[@id='searchResultDiv']/table/tbody/tr[2]/td[5]";
			String statePath 		= "//*[@id='searchResultDiv']/table/tbody/tr[2]/td[6]";
//			appID 					= ctrl.verifyData.getTextByXpath(appIdPath);
			appStatus.username 		= ctrl.verifyData.getTextByXpath(usernamePath);
			appStatus.usernameThai 	= ctrl.verifyData.getTextByXpath(usernameThaiPath);
			appStatus.role 			= ctrl.verifyData.getTextByXpath(rolePath);
			appStatus.analyze 		= ctrl.verifyData.getTextByXpath(analyzePath);
			appStatus.state 		= ctrl.verifyData.getTextByXpath(statePath);
			sendToLogCustom(logexestatus.PASS, logaction.Get, "username: " + appStatus.username);
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Get, "username -TimeoutException");
			return false;
		}
	
		sendToLogFinish();
		return true;
	}
	
	public String getUsername(){
		return appStatus.username;
	}

}
