package newsm2;
import com.SystemBase;

import controller.Controller;
import creditanalysis.BranchCA;
import creditanalysis.BranchCAComment;
import creditanalysis.BranchCACommentSendWork;
import creditanalysis.BranchCASendWork;
import creditanalysis.BranchSBROToCMAssign;
import creditanalysis.SecCA;
import creditanalysis.SecCAComment;
import creditanalysis.SecCACommentSendWork;
import creditanalysis.SecCASendWork;
import creditanalysis.SecCMDeptToCMAutoAssign;
import creditanalysis.SecSBROSecToCMDeptAssign;
import creditanalysis.SecSBROToCMDeptAssign;
import creditanalysis.SecSBROToSBROSecAssign;

public class CreditAnalysis extends BaseApplication {

	public CreditAnalysis(Controller ctrl, COMAutoElementConfig caeConfig) {
		this.ctrl = ctrl;
		this.caeConfig = caeConfig;
	}
	
	public boolean run(){
		ctrl.logCat.sendToLog("CreditAnalysis");
		creditAnalysis();
		return caeConfig.runableFlag;
	}
	
	private boolean creditAnalysis() {
		if (!caeConfig.runableFlag) return false;
		switch (caeConfig.CAPath) {

			case autoBranch:
				ctrl.logCat.sendToLog("auto branch");
			case branch:
				ctrl.logCat.sendToLog("branch");
				caeConfig.runableFlag = verifyState("NEWSM2SBRO", "มอบหมายงาน");
				branchSBROToCM();
				branchCA();
				branchCAComment();				
				break;

			case autoSection:
				ctrl.logCat.sendToLog("auto section");
			case section:
				ctrl.logCat.sendToLog("section");
				caeConfig.runableFlag = verifyState("NEWSM2SBRO", "เลือกส่งงานต่อ");
				secSBROToCMDeptAssign();
				//secSBROToSBROSecAssign();
				//secSBROSecToCMDeptAssign();
				secCMDeptToCMAutoAssign();
				secCA();
				secCAComment();
				break;
				
			default:
				break;
		}

		return caeConfig.runableFlag;
	}

	/*
	 *												Section Credit Analysis 
	 */
	private boolean secSBROToCMDeptAssign() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecSBROToCMDeptAssign(ctrl, caeConfig.CMDept).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean secSBROToSBROSecAssign() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecSBROToSBROSecAssign(ctrl, caeConfig.SBROSec).execute();

		logout();

		return caeConfig.runableFlag;
	}

	private boolean secSBROSecToCMDeptAssign() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBROSec, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecSBROSecToCMDeptAssign(ctrl, caeConfig.CMDept).execute();

		logout();

		return caeConfig.runableFlag;
	}

	private boolean secCMDeptToCMAutoAssign() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CMDept, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecCMDeptToCMAutoAssign(ctrl, caeConfig.CM).execute();

		logout();

		return caeConfig.runableFlag;
	}

	private boolean secCA() {
		if(caeConfig.CM.toLowerCase().matches("rr"))
			caeConfig.CM = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.CM, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecCA(ctrl).execute();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecCASendWork(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}

	private boolean secCAComment() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CMDept, SystemBase.LOR);
		gotoApp();
		
		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecCAComment(ctrl).execute();
		
		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new SecCACommentSendWork(ctrl, caeConfig.CreditPath).execute();

		logout();
		
		return caeConfig.runableFlag;
	}

	/*
	 *												Branch Credit Analysis 
	 */
	
	private boolean branchSBROToCM() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new BranchSBROToCMAssign(ctrl, caeConfig.CM).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean branchCA() {
		if(caeConfig.CM.toLowerCase().matches("rr"))
			caeConfig.CM = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.CM, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new BranchCA(ctrl).execute();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new BranchCASendWork(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean branchCAComment() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();
		
		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new BranchCAComment(ctrl).execute();
		
		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new BranchCACommentSendWork(ctrl, caeConfig.CreditPath).execute();

		logout();
		
		return caeConfig.runableFlag;
	}
	
}
