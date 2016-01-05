package com;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class GotoApp extends KeywordsCOM {

	private String appID;
	
	public GotoApp(Controller ctrl, String appID) {
		super.ctrl = ctrl;
		
		this.appID = appID;
		
		super.logoperation 		= log.LogTag.logoperation.GotoApp;
		super.logtab 			= log.LogTag.logtab.None;
		super.logsubtab 		= log.LogTag.logsubtab.None;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		try{
			ctrl.button.linkText("กล่องงาน");
			ctrl.button.linkText("กล่องงานส่วนตัว");
			ctrl.radio.name("selectedSearchType", 2);
			ctrl.type.name("searchAppId", appID);
			ctrl.button.xpath("//*[@id='content']/div/div/form/div[2]/button");
			ctrl.button.linkText(appID);
			sendToLogCustom(logexestatus.PASS, logaction.GotoApp, "appID: " + appID);
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.GotoApp, "appID: " + appID + " -TimeoutException");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.GotoApp, "appID: " + appID + " -InvalidElementStateException");
			return false;			
		}
		sendToLogFinish();
		return true;
	}
	
}
