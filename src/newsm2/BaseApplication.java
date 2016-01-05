package newsm2;
import com.GotoApp;
import com.Login;
import com.Logout;
import com.OpenBrowser;
import com.SystemBase;
import com.VerifyAppState;

import controller.Controller;

public class BaseApplication {
	
	protected Controller ctrl;
	protected COMAutoElementConfig caeConfig;
	
	protected boolean openBrowser(SystemBase system) {
		if (!caeConfig.runableFlag)
			return false;
		return caeConfig.runableFlag = new OpenBrowser(ctrl, system).execute();
	}
	
	protected boolean login(String username, SystemBase system) {
		if (!caeConfig.runableFlag)
			return false;
		return caeConfig.runableFlag = new Login(ctrl, username, "testuser", system).execute();
	}

	protected boolean logout() {
		if (!caeConfig.runableFlag)
			return false;
		return caeConfig.runableFlag = new Logout(ctrl).execute();
	}
	
	protected boolean gotoApp() {
		if (!caeConfig.runableFlag)
			return false;
		return caeConfig.runableFlag = new GotoApp(ctrl, caeConfig.appID).execute();
	}
	
	protected String searchForRole() {
		if (!caeConfig.runableFlag)
			return null;
		openBrowser(SystemBase.LOR);
		login(caeConfig.BRO, SystemBase.LOR);
		VerifyAppState swb = new VerifyAppState(ctrl, caeConfig.appID);
		caeConfig.runableFlag = swb.execute();		
		
		System.out.println("getRole");
		
		String res = swb.getUsername();
		
		System.out.println("getRole -" + res);
		
		logout();
		return res;
	}
	
	protected boolean verifyState(String role, String state) {
		if (!caeConfig.runableFlag)
			return false;
		openBrowser(SystemBase.LOR);
		login(caeConfig.BRO, SystemBase.LOR);
		if (!caeConfig.runableFlag)
			return false;
		VerifyAppState swb = new VerifyAppState(ctrl, caeConfig.appID);
		caeConfig.runableFlag = swb.execute();
		
		System.out.println("verifyState");
		
		boolean res = swb.isCorrectState(role, state);
		
		System.out.println("verifyState -" + res);
		
		logout();
		return res;
	}
	
}
