package com;

import base.KeywordsCOM;
import controller.Controller;

public class OpenBrowser extends KeywordsCOM {

	private SystemBase system;
	
	public OpenBrowser(Controller ctrl, SystemBase system) {
		super.ctrl = ctrl;
		
		this.system	= system;
		
		super.logoperation 		= log.LogTag.logoperation.OpenBrowser;
		super.logtab 			= log.LogTag.logtab.None;
		super.logsubtab 		= log.LogTag.logsubtab.None;
	}
	
	@Override
	public boolean execute() {
		initKeywords();
		sendToLogStart();
		
		String baseURL = null;
		if (system.equals(SystemBase.LOR)) {
			baseURL = ctrl.pathVariable.getLORBaseURL() + "login.jsp";
		} else if (system.equals(SystemBase.CMS)) {
			baseURL = ctrl.pathVariable.getCMSBaseURL() + "login.jsp";
		}
		else{
			return false;
		}
		
		ctrl.driver.get(baseURL);

		ctrl.driver.manage().window().maximize();
		
		sendToLogFinish();
		return true;
	}
	
}
