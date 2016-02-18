package testdata;

public class CellTag {
	public enum col{
		A, B, C, D, E, F, G, H, I, J, K
	}
	public enum inputType{
		dropdown, 
		dropdownx, 
		text, 
		radio, 
		date, 
		button, 
		checkbox, 
		popup, 
		alert,
		
		save,
		savedraft,
		verify,
		jsexe, 
		
		openbrowser
	}
	public enum fieldType{
		id, 
		name, 
		xpath, 
		linktext,
		value,
		
		urlMatches,
		urlContains,
		
		textMatchesById,
		textMatchesByName,
		textMatchesByXpath,
		textMatchesByLinkText,
		
		textContainsById,
		textContainsByName,
		textContainsByXpath,
		textContainsByLinkText,
		
		valueMatchesById,
		valueMatchesByName,
		valueMatchesByXpath,
		valueMatchesByLinkText,
		
		valueContainsById,
		valueContainsByName,
		valueContainsByXpath,
		valueContainsByLinkText
	}
	public enum collacteralType{
		Land,
		Building,
		Condo,
		LandNBuilding, 
		Vehicle, 
		None
	}
	public enum sheetType{
		Register,
		AddNormalCustomer,
		
		NCBOption,
		
		AddLoanCOM,
		
		CollLandBuilding,
		CollLand,
		CollBuilding,
		CollNormalWarranter,
		CollAccounting,
		
		CMSLandBuilding,
		CMSLand,
		CMSBuilding, 
		
		Document, 
		ReqDoc, 
		AttachDoc,
		
		LOR, 
		CMS, 
		GotoApp, 
		Login, 
		Logout, 
		SendWork, 
		CMSValuation, 
		None, 
		EndWork, 
		Wait, 
		ExcutiveSummary, 
		NCBSummary, 
		SearchWorkBox, 
		AddLegalCustomer, 
		CollLegalWarranter, 
		AddLoanDDA, 
		NCB, 
		CutomerNormal, 
		CutomerLegal, 
		LoanCOM, 
		LoanDDA, 
		CMSAccounting, 
		CMSLegalWar, 
		CMSNormalWar, 
			
		CA,
		Credit,
		
		BRO,
		SBRO,
		SBROSec,
		CMDept,
		CM, 
		
		KSCCOM,
		RCOM,
		BCOM, RCOMAdmin, BCOMAdmin, CMSec, CMGR, CACO, MMGR, AOM, LongTermLoan, PromissoryNote, BookGarantee, OD, CMSLottery, Commitment, System, Contract, ComProNote, MoneyNote, ComBookGarantee
	}
	
	public enum CATask{
		section, branch, autoBranch, autoSection
	}

	public enum CreditTask{
		KSCCOM, RCOM, BCOM, autoKSCCOM, autoRCOM, autoBCOM
	}

	public enum Commitment{
		PASS, NOTPASS, REQ_PASS, REQ_NOTPASS
	}
	
	public enum ContractTask{
		section, branch, autoBranch, autoSection
	}
	public enum ApprSystem{
		IN, OUT
	}
}
