package newsm2;
import com.AttachFiles;
import com.GotoApp;
import com.RequireDocuments;
import com.SystemBase;

import controller.Controller;
import creditapproval.*;
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
				switch(caeConfig.CreditSystem){
					case IN://------------------------------------------------------------------------------------
						switch(caeConfig.CAPath){
							case autoBranch:
							case branch:
								caeConfig.runableFlag = verifyState("NEWSM2SBRO", "เลือกคณะกรรมการ");
								BCOMSelectCommitteeIn(caeConfig.SBRO);
								break;
							case autoSection:
							case section:
								caeConfig.runableFlag = verifyState("NEWSM2CMDept", "เลือกคณะกรรมการ");
								BCOMSelectCommitteeIn(caeConfig.CMDept);
								break;
						}
						BCOMCommitment();
						BCOMPrintCommitment();
						break;
						
					case OUT://------------------------------------------------------------------------------------
						switch(caeConfig.CAPath){
							case autoBranch:
							case branch:
								caeConfig.runableFlag = verifyState("NEWSM2SBRO", "เลือกคณะกรรมการ");
								BCOMSelectCommitteeOut(caeConfig.SBRO);
								break;
							case autoSection:
							case section:
								caeConfig.runableFlag = verifyState("NEWSM2CMDept", "เลือกคณะกรรมการ");
								BCOMSelectCommitteeOut(caeConfig.CMDept);
								break;
						}
						//Select Committee Out
						BCOMRecordPrintCommitment();
						break;					
				}
				break;
				
			case autoRCOM:
				ctrl.logCat.sendToLog("AUTO RCOM");
			case RCOM:
				ctrl.logCat.sendToLog("RCOM");
				switch(caeConfig.CreditSystem){
					case IN://------------------------------------------------------------------------------------
						switch(caeConfig.CAPath){
							case autoBranch:
							case branch:
								caeConfig.runableFlag = verifyState("NEWSM2SBRO", "เลือกคณะกรรมการ");
								RCOMSelectCommitteeIn(caeConfig.SBRO);
								break;
							case autoSection:
							case section:
								caeConfig.runableFlag = verifyState("NEWSM2CMDept", "เลือกคณะกรรมการ");
								RCOMSelectCommitteeIn(caeConfig.CMDept);
								break;
						}
						RCOMCommitment();
						RCOMPrintCommitment();
						break;
						
					case OUT://------------------------------------------------------------------------------------
						switch(caeConfig.CAPath){
							case autoBranch:
							case branch:
								caeConfig.runableFlag = verifyState("NEWSM2SBRO", "เลือกคณะกรรมการ");
								RCOMSelectCommitteeOut(caeConfig.SBRO);
								break;
							case autoSection:
							case section:
								caeConfig.runableFlag = verifyState("NEWSM2CMDept", "เลือกคณะกรรมการ");
								RCOMSelectCommitteeOut(caeConfig.CMDept);
								break;
						}
						//Select Committee Out
						RCOMRecordPrintCommitment();
						break;				
				}
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

	private boolean RCOMSelectCommitteeIn(String user) {
		openBrowser(SystemBase.LOR);
		login(user, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMSelectCommitteeIn(ctrl, caeConfig.CreditSystem,  caeConfig.RCOM).execute();

		logout();

		return caeConfig.runableFlag;
	}

	private boolean RCOMSelectCommitteeOut(String user) {
		openBrowser(SystemBase.LOR);
		login(user, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMSelectCommitteeOut(ctrl, caeConfig.CreditSystem,  caeConfig.RCOM).execute();

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
			document();
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
		
		document();
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
			caeConfig.runableFlag = new RCOMRecordPrintCommitment(ctrl, caeConfig.CreditCommitment).execute();
		document();
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new RCOMRecordPrintCommitmentSendWork(ctrl).execute();
		
		logout();
		
		return caeConfig.runableFlag;
	}
	
	private boolean BCOMSelectCommitteeIn(String user) {
		openBrowser(SystemBase.LOR);
		login(user, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new BCOMSelectCommitteeIn(ctrl, caeConfig.CreditSystem, caeConfig.BCOM).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean BCOMSelectCommitteeOut(String user) {
		openBrowser(SystemBase.LOR);
		login(user, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new BCOMSelectCommitteeOut(ctrl, caeConfig.CreditSystem, caeConfig.BCOM).execute();

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
			document();
			if (internalFlag)
				internalFlag = new BCOMCommitmentSendWork(ctrl).execute();
			logout();
		}
		return caeConfig.runableFlag;
	}
	
	private boolean BCOMPrintCommitment() {
		caeConfig.RCOMADM = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.RCOMADM, SystemBase.LOR);
		gotoApp();
		
		document();
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new BCOMPrintCommitment(ctrl).execute();

		logout();

		return caeConfig.runableFlag;
	}
	private boolean BCOMRecordPrintCommitment(){
		caeConfig.RCOMADM = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.RCOMADM, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new BCOMRecordPrintCommitment(ctrl, caeConfig.CreditCommitment).execute();
		document();
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new BCOMRecordPrintCommitmentSendWork(ctrl).execute();
		
		logout();
		
		return caeConfig.runableFlag;
	}
	
	private boolean CustomerNotification() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new CustomerNotification(ctrl, caeConfig.ContractPath).execute();

		logout();

		return caeConfig.runableFlag;
	}
	
	private boolean EndApplication() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.BRO, SystemBase.LOR);
		gotoApp();
		
		if (caeConfig.runableFlag)
			caeConfig.runableFlag = new EndApplication(ctrl).execute();

		logout();

		caeConfig.runableFlag = false;
		return caeConfig.runableFlag;
	}
	
	private boolean document() {
		if (!caeConfig.runableFlag)
			return false;
		System.out.println("DOCUMENT DOCUMENT DOCUMENT DOCUMENT DOCUMENT DOCUMENT DOCUMENT");
		caeConfig.runableFlag = new RequireDocuments(ctrl).execute();
		caeConfig.runableFlag = new AttachFiles(ctrl).execute();
		return caeConfig.runableFlag;
	}
	
}
