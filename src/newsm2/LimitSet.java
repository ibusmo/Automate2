package newsm2;

import com.SystemBase;

import contract.PrepSBROToCMGRAssign;
import controller.Controller;

public class LimitSet extends BaseApplication {
	
	public LimitSet(Controller ctrl, COMAutoElementConfig caeConfig) {
		this.ctrl = ctrl;
		this.caeConfig = caeConfig;
	}
	
	public boolean run(){
		ctrl.logCat.sendToLog("Limit Set");
		setBudget();
		verifyBudget();
		return caeConfig.runableFlag;
	}
	
	private boolean setBudget() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CMGR, SystemBase.LOR);
		gotoApp();

		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepSBROToCMGRAssign(ctrl, caeConfig.CMGR).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean verifyBudget() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CMGR, SystemBase.LOR);
		gotoApp();

		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepSBROToCMGRAssign(ctrl, caeConfig.CMGR).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
}
