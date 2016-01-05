package creditapplication;

import org.openqa.selenium.TimeoutException;

import base.KeywordsCOM;
import controller.Controller;
import log.LogTag.logaction;
import log.LogTag.logexestatus;

public class Register extends KeywordsCOM {
	
	private String appID;
	
	public Register(Controller ctrl) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.Register;
		super.logtab 			= log.LogTag.logtab.None;
		super.logsubtab 		= log.LogTag.logsubtab.None;

		super.workSheetPath = "register_com_" + 1;
	}

	//Get AppID
	@Override
	protected boolean posExecute() {
		try{
			String textAppID = "current_appId";
			appID = ctrl.verifyData.getTextById(textAppID);
			sendToLogCustom(logexestatus.PASS, logaction.Get, "appID: " + appID);
		}catch(TimeoutException e){
			sendToLogCustom(logexestatus.FAIL, logaction.Get, "appID: " + appID);
			return false;
		}
		return true;
	}
	
	public String getAppID() {
		return appID;
	}
	
}
