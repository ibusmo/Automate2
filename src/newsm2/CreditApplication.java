package newsm2;
import com.AttachFiles;
import com.RequireDocuments;
import com.SystemBase;

import controller.Controller;
import creditapplication.AddCustomerList;
import creditapplication.AddLoanForm;
import creditapplication.BasicInformation;
import creditapplication.BasicInformationSendWork;
import creditapplication.CollateralAccount;
import creditapplication.CollateralBuilding;
import creditapplication.CollateralCheckOrNot;
import creditapplication.CollateralLand;
import creditapplication.CollateralLandAndBuilding;
import creditapplication.CollateralLottery;
import creditapplication.CollateralWarranterList;
import creditapplication.NCBOptionCheckOrNot;
import creditapplication.NCBSummary;
import creditapplication.NCBSummarySendWork;
import creditapplication.PrescreenSendWork;
import creditapplication.Register;
import creditapplication.VerifyAndCommitment;
import creditapplication.VerifyAndCommitmentSendWork;
import testdata.CellTag.sheetType;

public class CreditApplication extends BaseApplication {
	
	public CreditApplication(Controller ctrl, COMAutoElementConfig caeConfig) {
		this.ctrl = ctrl;
		this.caeConfig = caeConfig;
	}
	
	public boolean run(){
		ctrl.logCat.sendToLog("CreditApplication");
		creditApplication();
		return caeConfig.runableFlag;
	}
	
	private boolean creditApplication() {
		openBrowser(SystemBase.LOR);
		login(caeConfig.BRO, SystemBase.LOR);

		prescreen();
		NCBSummary();
		basicInformation();

		logout();

		verifyAndCommitment();
		return caeConfig.runableFlag;
	}
	
	private boolean prescreen() {
		register();
		caeConfig.appID = getAppID();

		NCBOptionCheckOrNot();
		CollateralCheckOrNot();
		addCustomer();
		addLoan();
		addCollateral();
		document();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new PrescreenSendWork(ctrl).execute();

		return caeConfig.runableFlag;
	}
	
	Register reg;

	private boolean register() {
		if (!caeConfig.runableFlag)
			return false;
		reg = new Register(ctrl);
		return caeConfig.runableFlag = reg.execute();
	}

	private String getAppID() {
		if (!caeConfig.runableFlag)
			return null;
		return reg.getAppID();
	}
	
	private boolean NCBOptionCheckOrNot() {
		if (!caeConfig.runableFlag)
			return false;
		return caeConfig.runableFlag = new NCBOptionCheckOrNot(ctrl, caeConfig.NCB).execute();
	}
	
	private boolean CollateralCheckOrNot() {
		if(caeConfig.CMSLandBuilding + caeConfig.CMSBuilding 
				+ caeConfig.CMSLand + caeConfig.CMSAccounting 
				+ caeConfig.CMSLottery + caeConfig.CMSNormalWar 
				+ caeConfig.CMSLegalWar 
				> 0){
			caeConfig.CollateralCheck = true;
		}
		if (!caeConfig.runableFlag)
			return false;
		return caeConfig.runableFlag = new CollateralCheckOrNot(ctrl, caeConfig.CollateralCheck).execute();
	}
	
	private boolean addCustomer() {
		if (!caeConfig.runableFlag)
			return false;
		
		if (caeConfig.customerQueue.equals(sheetType.AddNormalCustomer)) {
			for (int i = 1; i <= caeConfig.CutomerNormal; i++) {
				if (!caeConfig.runableFlag)
					return false;
				Boolean mainCustomerFlag = i==1 ? true : false;
				caeConfig.runableFlag = new AddCustomerList(ctrl, sheetType.AddNormalCustomer, caeConfig.CutomerNormalList[i-1], mainCustomerFlag).execute();
//				caeConfig.runableFlag = new AddCustomer(ctrl, sheetType.AddCustomerNormal, i).execute();
			}
			for (int i = 1; i <= caeConfig.CutomerLegal; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new AddCustomerList(ctrl, sheetType.AddLegalCustomer, caeConfig.CutomerLegalList[i-1], false).execute();
//				caeConfig.runableFlag = new AddCustomer(ctrl, sheetType.AddCustomerLegal, i).execute();
			}
		} else if (caeConfig.customerQueue.equals(sheetType.AddLegalCustomer)) {
			for (int i = 1; i <= caeConfig.CutomerLegal; i++) {
				if (!caeConfig.runableFlag)
					return false;
				Boolean mainCustomerFlag = i==1 ? true : false;
				caeConfig.runableFlag = new AddCustomerList(ctrl, sheetType.AddLegalCustomer, caeConfig.CutomerLegalList[i-1], mainCustomerFlag).execute();
//				caeConfig.runableFlag = new AddCustomer(ctrl, sheetType.AddLegalCustomer, i).execute();
			}
			for (int i = 1; i <= caeConfig.CutomerNormal; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new AddCustomerList(ctrl, sheetType.AddNormalCustomer, caeConfig.CutomerNormalList[i-1], false).execute();
//				caeConfig.runableFlag = new AddCustomer(ctrl, sheetType.AddNormalCustomer, i).execute();
			}
		}
		return caeConfig.runableFlag;
	}

	private boolean addLoan() {
		if (!caeConfig.runableFlag)
			return false;

			for (int i = 1; i <= caeConfig.LongTermLoan; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new AddLoanForm(ctrl, sheetType.LongTermLoan, i).execute();
			}
			for (int i = 1; i <= caeConfig.PromissoryNote; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new AddLoanForm(ctrl, sheetType.PromissoryNote, i).execute();
			}
			for (int i = 1; i <= caeConfig.BookGarantee; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new AddLoanForm(ctrl, sheetType.BookGarantee ,i).execute();
			}
			for (int i = 1; i <= caeConfig.OD; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new AddLoanForm(ctrl, sheetType.OD ,i).execute();
			}
		
		return caeConfig.runableFlag;
	}

	private boolean addCollateral() {
		if (!caeConfig.runableFlag)
			return false;
		int collIndex = 1;
			for (int i = 1; i <= caeConfig.CMSLandBuilding; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new CollateralLandAndBuilding(ctrl, i, collIndex++).execute();
			}
			for (int i = 1; i <= caeConfig.CMSBuilding; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new CollateralBuilding(ctrl, i, collIndex++).execute();
			}
			for (int i = 1; i <= caeConfig.CMSLand; i++) {
				if (!caeConfig.runableFlag)
					return false;
				collIndex++;
				caeConfig.runableFlag = new CollateralLand(ctrl, i).execute();
			}
			for (int i = 1; i <= caeConfig.CMSAccounting; i++) {
				if (!caeConfig.runableFlag)
					return false;
				collIndex++;
				caeConfig.runableFlag = new CollateralAccount(ctrl, i).execute();
			}
			for (int i = 1; i <= caeConfig.CMSLottery; i++) {
				if (!caeConfig.runableFlag)
					return false;
				collIndex++;
				caeConfig.runableFlag = new CollateralLottery(ctrl, i).execute();
			}
			for (int i = 1; i <= caeConfig.CMSNormalWar; i++) {
				if (!caeConfig.runableFlag)
					return false;
				collIndex++;
				caeConfig.runableFlag = new CollateralWarranterList(ctrl, sheetType.CollNormalWarranter, caeConfig.CMSNormalWarList[i-1]).execute();
//				caeConfig.runableFlag = new CollateralWarranter(ctrl, sheetType.CollWarranterNormal, i, collIndex++).execute();
			}
			for (int i = 1; i <= caeConfig.CMSLegalWar; i++) {
				if (!caeConfig.runableFlag)
					return false;
				collIndex++;
				caeConfig.runableFlag = new CollateralWarranterList(ctrl, sheetType.CollLegalWarranter, caeConfig.CMSLegalWarList[i-1]).execute();
//				caeConfig.runableFlag = new CollateralWarranter(ctrl, sheetType.CollWarranterLegal, i, collIndex++).execute();
			}
		return caeConfig.runableFlag;
	}

	private boolean document() {
		if (!caeConfig.runableFlag)
			return false;
		// If customer is Normal -> DOCUMENT
		if (caeConfig.CutomerNormal > 0) {
			if (!caeConfig.runableFlag)
				return false;
			caeConfig.runableFlag = new RequireDocuments(ctrl).execute();
			if (!caeConfig.runableFlag)
				return false;
			caeConfig.runableFlag = new AttachFiles(ctrl).execute();
		}
		return caeConfig.runableFlag;
	}
	
	private boolean NCBSummary() {
		if (!caeConfig.runableFlag)
			return false;
		// If customer is Legal and NCB option is true -> NCB
		if (caeConfig.CutomerLegal > 0 && caeConfig.NCB) {
			gotoApp();
			for (int i = 1; i <= caeConfig.CutomerNormal + caeConfig.CutomerLegal; i++) {
				if (!caeConfig.runableFlag)
					return false;
				caeConfig.runableFlag = new NCBSummary(ctrl, 1, i).execute();
			}

			if (!caeConfig.runableFlag)
				return false;
			caeConfig.runableFlag = new NCBSummarySendWork(ctrl).execute();
		}
		return caeConfig.runableFlag;
	}
	
	private boolean basicInformation() {
		if (!caeConfig.runableFlag)
			return false;
		gotoApp();
		for (int i = 1; i <= caeConfig.CutomerNormal + caeConfig.CutomerLegal; i++) {
			if (!caeConfig.runableFlag)
				return false;
			caeConfig.runableFlag = new NCBSummary(ctrl, 1, i).execute();
		}
		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new BasicInformation(ctrl, 1).execute();
		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new BasicInformationSendWork(ctrl).execute();
		return caeConfig.runableFlag;
	}
	
	private boolean verifyAndCommitment() {
		caeConfig.SBRO = searchForRole();
		openBrowser(SystemBase.LOR);
		login(caeConfig.SBRO, SystemBase.LOR);
		gotoApp();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new VerifyAndCommitment(ctrl, 1).execute();

		if (!caeConfig.runableFlag)
			return false;
		caeConfig.runableFlag = new VerifyAndCommitmentSendWork(ctrl, caeConfig.CAPath).execute();

		logout();
		return caeConfig.runableFlag;
	}
	
}
