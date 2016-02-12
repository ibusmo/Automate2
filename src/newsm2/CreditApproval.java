package newsm2;
import com.GotoApp;
import com.SystemBase;

import controller.Controller;
import creditapproval.*;
import testdata.CellTag.CATask;
import testdata.CellTag.Commitment;

public class CreditApproval extends BaseApplication {

	public CreditApproval(Controller ctrl, COMAutoElementConfig caeConfig) {
		this.ctrl = ctrl;
		this.caeConfig = caeConfig;
	}

	public boolean run(){
		ctrl.logCat.sendToLog("CreditApproval");
		if (!caeConfig.runableFlag)
			return false;
		
		switch (caeConfig.CreditPath) {
		
			case autoBCOM:
				ctrl.logCat.sendToLog("AUTO BCOM");
			case BCOM:
				ctrl.logCat.sendToLog("BCOM");
				switch(caeConfig.CAPath){
					case autoBranch:
					case branch:
						caeConfig.runableFlag = verifyState("NEWSM2SBRO", "เลือกคณะกรรมการ");
						break;
					case autoSection:
					case section:
						caeConfig.runableFlag = verifyState("NEWSM2CMDept", "เลือกคณะกรรมการ");
						break;
				}
				BCOMSelectCommittee();
				BCOMCommitment();
				break;
				
			case autoRCOM:
				ctrl.logCat.sendToLog("AUTO RCOM");
			case RCOM:
				ctrl.logCat.sendToLog("RCOM");
				switch(caeConfig.CAPath){
					case autoBranch:
					case branch:
						caeConfig.runableFlag = verifyState("NEWSM2SBRO", "เลือกคณะกรรมการ");
						break;
					case autoSection:
					case section:
						caeConfig.runableFlag = verifyState("NEWSM2CMDept", "เลือกคณะกรรมการ");
						break;
				}
				RCOMSelectCommittee();
				//RCOMCommitment();
				/*if(caeConfig.CreditCommitment.equals(Commitment.REQ_PASS) || caeConfig.CreditCommitment.equals(Commitment.REQ_NOTPASS)){
					RCOMRecordPrintCommitment();
				}
				else{
					RCOMPrintCommitment();
				}*/
				break;
				
			case autoKSCCOM:
				ctrl.logCat.sendToLog("AUTO KSCCOM");			
			case KSCCOM:
				ctrl.logCat.sendToLog("KSCCOM");
				caeConfig.runableFlag = verifyState("KSCCOM", "พิจารณาอนุมัติสินเชื่อ(บันทึกมติ)");
				KSCCOMCommitment();
				if(caeConfig.CreditCommitment.equals(Commitment.PASS)){
					//Approval Record
					KSCCOMCondition();
					KSCCOMConditionVerify();
				}
				break;
		
		}
		CustomerNotification();
		if(caeConfig.CreditCommitment.equals(Commitment.NOTPASS) || caeConfig.CreditCommitment.equals(Commitment.REQ_NOTPASS)){
			EndApplication();
		}
		return caeConfig.runableFlag;
	}
	
	private boolean KSCCOMCommitment() {
		caeConfig.KSCCOM = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.KSCCOM, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new KSCCOMCommitment(ctrl, caeConfig.CreditCommitment).execute();
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMCommitmentSendWork(ctrl).execute();
		
		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean KSCCOMCondition() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.CM, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new KSCCOMCondition(ctrl).execute();
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new KSCCOMCommitmentSendWork(ctrl).execute();
		
		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean KSCCOMConditionVerify() {
		String localUsername;
		switch(caeConfig.CAPath){
			case section:
				localUsername = caeConfig.CMDept;
				break;
			case branch:
				localUsername = caeConfig.CMSec = searchForRole();
				break;
			default:
				localUsername = caeConfig.CMDept;
				break;
		}
		
		openBrowser(SystemBase.LOR);
		login(localUsername, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new KSCCOMConditionVerify(ctrl).execute();
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMCommitmentSendWork(ctrl).execute();
		
		logout();

		return caeConfig.runableFlag;
	}

	private boolean RCOMSelectCommittee() {
		openBrowser(SystemBase.LOR);
		String tmpUser = null;
		if(caeConfig.CAPath.equals(CATask.branch) || caeConfig.CAPath.equals(CATask.autoBranch)){
			tmpUser = caeConfig.SBRO;
		}
		else if(caeConfig.CAPath.equals(CATask.section) || caeConfig.CAPath.equals(CATask.autoSection)){
			tmpUser = caeConfig.CMDept;
		}
		login(tmpUser, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMSelectCommittee(ctrl, caeConfig.CreditSystem,  caeConfig.RCOM).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean RCOMCommitment() {
		if (!caeConfig.runableFlag)	return false;
		for(String currentUser : caeConfig.RCOM){
			openBrowser(SystemBase.LOR);
			login(currentUser, SystemBase.LOR);
			boolean internalFlag = true;
			internalFlag = new GotoApp(ctrl, caeConfig.appID).execute();
			if (internalFlag)
				internalFlag = new RCOMCommitment(ctrl, caeConfig.CreditCommitment).execute();
			if (internalFlag)
				internalFlag = new RCOMCommitmentSendWork(ctrl).execute();
			logout();
		}
		return caeConfig.runableFlag;
	}
	
	private boolean RCOMPrintCommitment() {
		caeConfig.RCOMADM = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.RCOMADM, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMPrintCommitment(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}
	private boolean RCOMRecordPrintCommitment(){
		caeConfig.RCOMADM = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.RCOMADM, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMRecordPrintCommitment(ctrl, 
					caeConfig.CreditCommitment.equals(Commitment.REQ_PASS) ? Commitment.PASS : Commitment.NOTPASS
			).execute();
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMRecordPrintCommitmentSendWork(ctrl).execute();
		
		logout();
		
		return caeConfig.runableFlag;
	}
	
	private boolean BCOMSelectCommittee() {
		openBrowser(SystemBase.LOR);
		String tmpUser = null;
		if(caeConfig.CAPath.equals(CATask.branch) || caeConfig.CAPath.equals(CATask.autoBranch)){
			tmpUser = caeConfig.SBRO;
		}
		else if(caeConfig.CAPath.equals(CATask.section) || caeConfig.CAPath.equals(CATask.autoSection)){
			tmpUser = caeConfig.CMDept;
		}
		login(tmpUser, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new BCOMSelectCommittee(ctrl, caeConfig.CreditSystem, caeConfig.BCOM).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean BCOMCommitment() {
		if (!caeConfig.runableFlag)	return false;
		for(String currentUser : caeConfig.BCOM){
			openBrowser(SystemBase.LOR);
			login(currentUser, SystemBase.LOR);
			boolean internalFlag = true;
			internalFlag = new GotoApp(ctrl, caeConfig.appID).execute();
			if (internalFlag)
				internalFlag = new BCOMCommitment(ctrl, caeConfig.CreditCommitment).execute();
			if (internalFlag)
				internalFlag = new BCOMCommitmentSendWork(ctrl).execute();
			logout();
		}
		return caeConfig.runableFlag;
	}
	
	private boolean CustomerNotification() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new CustomerNotification(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean EndApplication() {
//		openBrowser(SystemBase.LOR);
//		login(caeConfig.BRO, SystemBase.LOR);
//		gotoApp();
//		
//		if (caeConfig.runableFlag)
//			caeConfig.runableFlag = new EndApplication(ctrl).execute();
//
//		logout();

		caeConfig.runableFlag = false;
		return caeConfig.runableFlag;
	}
	
}
