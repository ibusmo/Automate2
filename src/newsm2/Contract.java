package newsm2;
import com.SystemBase;

import contract.*;
import controller.Controller;

public class Contract extends BaseApplication {
	
	public Contract(Controller ctrl, COMAutoElementConfig caeConfig) {
		this.ctrl = ctrl;
		this.caeConfig = caeConfig;
	}
	
	public boolean run(){
		ctrl.logCat.sendToLog("Contract");
		contractPreparation();
		ContractSigning();
		return caeConfig.runableFlag;
	}

	private boolean ContractSigning() {
		ctrl.logCat.sendToLog("Contract Signing");
		loanAgreement();
		mortgage();
		return caeConfig.runableFlag;
	}

	private boolean contractPreparation() {
		if (!caeConfig.runableFlag) return false;
		
		ctrl.logCat.sendToLog("Contract Preparation");
		caeConfig.runableFlag = verifyState("SBRO", "เลือกส่งงานต่อ");
		prepSBROToCMGRAssign();
		prepCMGRToCACOAssign();
		makeContract();
		contractLoanCollVerify();
		
		return caeConfig.runableFlag;		
	}
	
	private boolean loanAgreement() {				
		ctrl.logCat.sendToLog("Contract Loan Agreement");
		caeConfig.runableFlag = verifyState("CAOC", "พิมพ์สัญญาสินเชื่อ/นัดหมายลูกค้าเพื่อลงนาม") 
								|| verifyState("CMGR", "เลือกส่งงานต่อ");
		loanAgreePrepareAccept();
		loanAgreeAccept();
		
		return caeConfig.runableFlag;		
	}
	
	private boolean mortgage() {
		if(caeConfig.CMSLandBuilding + caeConfig.CMSLand + caeConfig.CMSBuilding <= 0)
			return caeConfig.runableFlag;
		ctrl.logCat.sendToLog("Contract Mortgage");
		caeConfig.runableFlag = verifyState("CMGR", "เลือกส่งงานต่อ");
		
		return caeConfig.runableFlag;
	}

	private boolean prepSBROToCMGRAssign() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();

		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepSBROToCMGRAssign(ctrl, caeConfig.CMGR).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean prepCMGRToCACOAssign() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CMGR, SystemBase.LOR);
		gotoApp();

		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepCMGRToCACOAutoAssign(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean makeContract() {
		caeConfig.CAOC = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.CAOC, SystemBase.LOR);
		gotoApp();

		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepMakeContractAdditionalNew(ctrl).execute();
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepMakeContractAdditional(ctrl).execute();
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepMakeContractLoanReport(ctrl).execute();
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepMakeContractCollReport(ctrl).execute();
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepMakeContractSignDate(ctrl).execute();
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepMakeContractSendWork(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}

	private boolean contractLoanCollVerify() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CMGR, SystemBase.LOR);
		gotoApp();
		
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepContractVerify(ctrl).execute();

		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new PrepContractVerifySendWork(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}

	private boolean loanAgreePrepareAccept() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CAOC, SystemBase.LOR);
		gotoApp();
		
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new LoanAgreePrepareAccept(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}	
	
	private boolean loanAgreeAccept() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CMGR, SystemBase.LOR);
		gotoApp();
		
		if(caeConfig.runableFlag)
			caeConfig.runableFlag = new LoanAgreeAccept(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
}
