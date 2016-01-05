package com;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.UnreachableBrowserException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class Login extends KeywordsCOM {
	
	private String username;
	private String password;
	private SystemBase system;
	
	public Login(Controller ctrl, String username, String password, SystemBase system) {
		super.ctrl = ctrl;
		
		this.username	= username;
		this.password	= password;
		this.system		= system;
		
		super.logoperation 		= log.LogTag.logoperation.Login;
		super.logtab 			= log.LogTag.logtab.None;
		super.logsubtab 		= log.LogTag.logsubtab.None;
	}
	
	@Override
	public boolean execute() {
		if(initKeywords()==false)					return false;
		sendToLogStart();
		try{
			
			if(ctrl.verifyData.urlContains("login") 
					&& !ctrl.verifyData.urlContains("current")){
				ctrl.type.id("j_username", username);		
				ctrl.type.id("j_password", password);
				ctrl.button.id("wp-submit");	
				sendToLogCustom(logexestatus.PASS, logaction.Fill_login, "Clear login as: -" + username);
			}
			
			if(ctrl.verifyData.urlContains("login") 
					&& ctrl.verifyData.urlContains("urrent")){
				if(system.equals(SystemBase.LOR)) 
					ctrl.type.id("j_password", "testuser");
				ctrl.button.id("wp-submit");
				sendToLogCustom(logexestatus.PASS, logaction.Fill_login, "Kick login as: -" + username);
			}

			if(system.equals(SystemBase.LOR)
					&& ctrl.verifyData.urlContains("inboxAction.do") 
					&& ctrl.verifyData.textMatchesByLinkText("กล่องงานส่วนตัว", "กล่องงานส่วนตัว"))
			{
				sendToLogCustom(logexestatus.PASS, logaction.Fill_login, "Verify login");				
			}
			else if(system.equals(SystemBase.CMS) 
					&& ctrl.verifyData.urlContains("inboxAppraisalMenuAction.do") 
					&& ctrl.verifyData.textMatchesByLinkText("การประเมินราคาใหม่", "การประเมินราคาใหม่"))
			{
				sendToLogCustom(logexestatus.PASS, logaction.Fill_login, "Verify login");
			}
			else{
				sendToLogCustom(logexestatus.FAIL, logaction.Fill_login, "Verify login");
				return false;
			}
			
		}catch (TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Fill_login, "Time Out");
			return false;
		}catch (InvalidElementStateException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Fill_login, "InvalidElementStateException");
			return false;			
		}catch (UnreachableBrowserException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Fill_login, "UnreachableBrowserException");
			return false;	
		}catch(NoSuchWindowException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Fill_login, "NoSuchWindowException");
			return false;	
		}
		sendToLogFinish();
		return true;
	}
	
}
