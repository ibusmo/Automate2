package newsm2;
import com.SystemBase;

import autocms.ListOfCMS;
import controller.Controller;

public class CMSValuations extends BaseApplication {

	public CMSValuations(Controller ctrl, COMAutoElementConfig caeConfig) {
		this.ctrl = ctrl;
		this.caeConfig = caeConfig;
	}

	public boolean run(){
		ctrl.logCat.sendToLog("CMSValuations");
		CMSValuation2(caeConfig.appID, caeConfig.CMS);
		return caeConfig.runableFlag;
	}
	
	private boolean CMSValuation2(String appID, String username) {
		if (caeConfig.CMSLandBuilding > 0 || caeConfig.CMSBuilding > 0 || caeConfig.CMSLand > 0) {
			openBrowser(SystemBase.CMS);

			login(username, SystemBase.CMS);

			if (!caeConfig.runableFlag)
				return false;
			caeConfig.runableFlag = new ListOfCMS(ctrl, appID).execute();
			capture("CMS");
			logout();
		}
		return caeConfig.runableFlag;
	}

	
}
