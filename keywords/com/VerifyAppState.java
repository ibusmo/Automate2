package com;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import log.LogTag.logaction;
import log.LogTag.logexestatus;
import base.KeywordsCOM;
import controller.Controller;

public class VerifyAppState extends KeywordsCOM {

	AppStatusElementObj appStatus;
	
	//viewStatus('038309580126')
	
	public VerifyAppState(Controller ctrl, String appID){
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.SearchWorkBOx;
		super.logtab 			= log.LogTag.logtab.SearchWorkBOx;
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
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "Tab ค้นหาใบคำขอสินเชื่อ -TimeoutException");
			return false;
		}catch (UnreachableBrowserException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "Tab ค้นหาใบคำขอสินเชื่อ -UnreachableBrowserException");
			return false;
		}
		
		try {
			// เลขที่ใบคำขอสินเชื่อ
			ctrl.type.id("searchCaNumber", appStatus.appID);			
			sendToLogCustom(logexestatus.PASS, logaction.Text, "เลขที่ใบคำขอสินเชื่อ : " + appStatus.appID);
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Text, "เลขที่ใบคำขอสินเชื่อ : -TimeoutException " + appStatus.appID);
			return false;
		}catch (UnreachableBrowserException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Text, "เลขที่ใบคำขอสินเชื่อ : -UnreachableBrowserException " + appStatus.appID);
			return false;
		}
		
		try {
			// คลิก ค้นหา
			ctrl.button.xpath("//*[@id='searchApplicationDiv']/div[2]/center/button[1]");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "ค้นหา");
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "ค้นหา -TimeoutException");
			return false;
		}catch (UnreachableBrowserException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "ค้นหา -UnreachableBrowserException");
			return false;
		}
		
		try {
			// คลิก ติดตามสถานะ
			ctrl.button.xpath("//*[@id='searchResultDiv']/table/tbody/tr[2]/td[8]/button[2]");
			sendToLogCustom(logexestatus.PASS, logaction.Click, "ติดตามสถานะ");
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "ติดตามสถานะ -TimeoutException");
			return false;
		}catch (UnreachableBrowserException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Click, "ติดตามสถานะ -UnreachableBrowserException");
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
			sendToLogCustom(logexestatus.PASS, logaction.Get, 
					"appID: " + appStatus.appID + ", " +
					"username: " + appStatus.username + ", " +
					"usernameThai: " + appStatus.usernameThai + ", " +
					"role: " + appStatus.role + ", " +
					"analyze: " + appStatus.analyze + ", " +
					"state: " + appStatus.state + ", "
					);
		}catch (TimeoutException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Get, "username -TimeoutException");
			return false;
		}catch (UnreachableBrowserException e) {
			sendToLogCustom(logexestatus.FAIL, logaction.Get, "username -UnreachableBrowserException");
			return false;
		}
	
		sendToLogFinish();
		return true;
	}
	
	public String getUsername(){
		return appStatus.username;
	}	

	public String getUsernameThai(){
		return appStatus.usernameThai;
	}

	public String getRole(){
		return appStatus.role;
	}
	
	public String getAnalyze(){
		return appStatus.analyze;
	}

	public String getState(){
		return appStatus.state;
	}
	
	public boolean isCorrectState(String expRole, String expState){
		if(expRole==null || expState==null)	return false;
		if(appStatus.role.contains(expRole) 
				&& appStatus.state.contains(expState)){
			sendToLogCustom(logexestatus.PASS, logaction.Verify, "expRole: " + expRole + ", " + "expState: " + expState);
			return true;
		}
		else{
			sendToLogCustom(logexestatus.FAIL, logaction.Verify, "expRole: " + expRole + ", " + "expState: " + expState);
			return false;
		}
	}
	
}
