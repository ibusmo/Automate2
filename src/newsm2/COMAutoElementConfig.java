package newsm2;
import java.util.List;

import testdata.CellTag.CATask;
import testdata.CellTag.Commitment;
import testdata.CellTag.CreditTask;
import testdata.CellTag.sheetType;
import testdata.DataAutoElementObj;

public class COMAutoElementConfig {
	
	public boolean runableFlag = true;
	
	public String appID = null;
	public int delayTime = 100;
	public Boolean NCB = false;
	public int CutomerNormal = 0;
	public String[] CutomerNormalList = null;
	public int CutomerLegal = 0;
	public String[] CutomerLegalList = null;
	public sheetType customerQueue = null;

	public int LongTermLoan = 0;
	public int PromissoryNote = 0;
	public int BookGarantee = 0;
	public int OD = 0;

	public int CMSLandBuilding = 0;
	public int CMSBuilding = 0;
	public int CMSLand = 0;
	public int CMSAccounting = 0;
	public int CMSLottery = 0;
	public int CMSLegalWar = 0;
	public String[] CMSLegalWarList = null;
	public int CMSNormalWar = 0;
	public String[] CMSNormalWarList = null;

	public CATask CAPath = null;
	public CreditTask CreditPath = null;
	public Commitment CreditCommitment = null;

	public String CMS = null;
	
	public String BRO = null;
	public String SBRO = null;
	
	public String SBROSec = null;

	public String CM = null;
	
	public String CMDept = null;

	public String CMSec = null;

	public String CMGR = null;

	public String CAOC = null;
	
	public String KSCCOM = null;
	
	public String[] RCOM = null;
	
	public String[] BCOM = null;

	public String RCOMADM = null;
	public String BCOMADM = null;

	public String MMGR = null;
	
	public String AOM = null;
	
	public COMAutoElementConfig(boolean runableFlag) {
		this.runableFlag = runableFlag;
	}

	public void loadConfigAndSetting(List<DataAutoElementObj> eaeo) {
		// SETDATA
		for (DataAutoElementObj obj : eaeo) {
			switch (obj.sheet) {
			//Credit Application
			case BRO:
				BRO = obj.remark;
				break;	
			case SBRO:
				SBRO = obj.remark;
				break;		
			//CMS
			case CMS:
				CMS = obj.remark;
				break;
			//Credit Analysis
			case SBROSec:
				SBROSec = obj.remark;
				break;
			case CMDept:
				CMDept = obj.remark;
				break;
			case CM:
				CM = obj.remark;
				break;
			case CMSec:
				CMSec = obj.remark;
				break;
			case CMGR:
				CMGR = obj.remark;
				break;
			case CACO:
				CAOC = obj.remark;
				break;
			//Credit Approval
			case KSCCOM:
				KSCCOM = obj.remark;
				break;
			case RCOM:
				RCOM = obj.remark.split(",", -1);
				break;
			case BCOM:
				BCOM = obj.remark.split(",", -1);
				break;
			case RCOMAdmin:
				RCOMADM = obj.remark;
				break;
			case BCOMAdmin:
				BCOMADM = obj.remark;
				break;
			//Contract
			case MMGR:
				MMGR = obj.remark;
				break;
			case AOM:
				AOM = obj.remark;
				break;
				
			case NCB:
				NCB = obj.remark.matches("yes") ? true : false;
				break;
			case CutomerNormal:
				CutomerNormal = obj.index;
				CutomerNormalList = obj.remark.split(",", -1);
				if (customerQueue == null && CutomerNormal != 0) {
					customerQueue = sheetType.AddNormalCustomer;
				}
				break;
			case CutomerLegal:
				CutomerLegal = obj.index;
				CutomerLegalList = obj.remark.split(",", -1);
				if (customerQueue == null && CutomerLegal != 0) {
					customerQueue = sheetType.AddLegalCustomer;
				}
				break;
				
			case LongTermLoan:
				LongTermLoan = obj.index;
				break;
			case PromissoryNote:
				PromissoryNote = obj.index;
				break;
			case BookGarantee:
				BookGarantee = obj.index;
				break;
			case OD:
				OD = obj.index;
				break;

			case CMSLandBuilding:
				CMSLandBuilding = obj.index;
				break;
			case CMSBuilding:
				CMSBuilding = obj.index;
				break;
			case CMSLand:
				CMSLand = obj.index;
				break;
			case CMSAccounting:
				CMSAccounting = obj.index;
				break;
			case CMSLottery:
				CMSLottery = obj.index;
				break;
			case CMSLegalWar:
				CMSLegalWar = obj.index;
				CMSLegalWarList = obj.remark.split(",", -1);
				break;
			case CMSNormalWar:
				CMSNormalWar = obj.index;
				CMSNormalWarList = obj.remark.split(",", -1);
				break;
			case CA:
				if(obj.remark.toLowerCase().matches("section")){
					CAPath = CATask.section;
				}
				else if(obj.remark.toLowerCase().matches("branch")){
					CAPath = CATask.branch;
				}
				// auto (section) - auto (branch)
				else if(obj.remark.toLowerCase().contains("section")){
					CAPath = CATask.autoSection;
				}
				else if(obj.remark.toLowerCase().contains("branch")){
					CAPath = CATask.autoBranch;
				}
				break;
			case Credit:
				if(obj.remark.toLowerCase().matches("ksccom")){
					CreditPath = CreditTask.KSCCOM;
				}
				else if(obj.remark.toLowerCase().matches("rcom")){
					CreditPath = CreditTask.RCOM;
				}
				else if(obj.remark.toLowerCase().matches("bcom")){
					CreditPath = CreditTask.BCOM;
				}
				// auto (KSCCOM) - auto (RCOM) - auto (BCOM)
				else if(obj.remark.toLowerCase().contains("ksccom")){
					CreditPath = CreditTask.autoKSCCOM;
				}
				else if(obj.remark.toLowerCase().contains("rcom")){
					CreditPath = CreditTask.autoRCOM;
				}
				else if(obj.remark.toLowerCase().contains("bcom")){
					CreditPath = CreditTask.autoBCOM;
				}
				break;
			case Commitment:
				if(obj.remark.toLowerCase().matches("pass")){
					CreditCommitment = Commitment.PASS;
				}
				else if(obj.remark.toLowerCase().matches("not")){
					CreditCommitment = Commitment.NOTPASS;
				}
				else if(obj.remark.toLowerCase().matches("req_pass")){
					CreditCommitment = Commitment.REQ_PASS;
				}
				else if(obj.remark.toLowerCase().matches("req_not")){
					CreditCommitment = Commitment.REQ_NOTPASS;
				}
				break;
			default:
				break;
			}
		}
	}
	
	public void showSetting() {
		String strTmp = "";
		
		System.out.println("appID " + appID);
		System.out.println("delayTime " + delayTime);
		System.out.println("NCB " + NCB);

		System.out.println("CutomerNormal " + CutomerNormal);
		strTmp = "";
		if(CutomerNormalList != null) for(String str : CutomerNormalList){ strTmp += str + " "; }
		System.out.println("CutomerNormalList " + strTmp);
		System.out.println("CutomerLegal " + CutomerLegal);
		strTmp = "";
		if(CutomerLegalList != null) for(String str : CutomerLegalList){ strTmp += str + " "; }
		System.out.println("CutomerLegalList " + strTmp);
		System.out.println("customerQueue " + customerQueue);

		System.out.println("LongTermLoan " + LongTermLoan);
		System.out.println("PromissoryNote " + PromissoryNote);
		System.out.println("BookGarantee " + BookGarantee);
		System.out.println("OD " + OD);

		System.out.println("CMSLandBuilding " + CMSLandBuilding);
		System.out.println("CMSBuilding " + CMSBuilding);
		System.out.println("CMSLand " + CMSLand);
		System.out.println("CMSAccounting " + CMSAccounting);
		System.out.println("CMSLottery " + CMSLottery);
		
		System.out.println("CMSLegalWar " + CMSLegalWar);
		strTmp = "";
		if(CMSLegalWarList != null) for(String str : CMSLegalWarList){ strTmp += str + " "; }
		System.out.println("CMSLegalWarList " + strTmp);
		
		System.out.println("CMSNormalWar " + CMSNormalWar);
		strTmp = "";
		if(CMSNormalWarList != null) for(String str : CMSNormalWarList){ strTmp += str + " "; }
		System.out.println("CMSNormalWarList " + strTmp);

		System.out.println("CAPath " + CAPath);
		System.out.println("CreditPath " + CreditPath);
		System.out.println("CreditCommitment " + CreditCommitment);

		System.out.println("CMS " + CMS);
		System.out.println("BRO " + BRO);
		System.out.println("SBRO " + SBRO);
		System.out.println("SBROSec " + SBROSec);
		
		System.out.println("CM " + CM);
		System.out.println("CMDept " + CMDept);
		System.out.println("CMSec " + CMSec);
		System.out.println("CMGR " + CMGR);
		System.out.println("CACO " + CAOC);
		
		System.out.println("KSCCOM " + KSCCOM);
		strTmp = "";
		if(RCOM != null) for(String str : RCOM){ strTmp += str + " "; }
		System.out.println("RCOM " + strTmp);
		strTmp = "";
		if(BCOM != null) for(String str : BCOM){ strTmp += str + " "; }
		System.out.println("BCOM " + strTmp);
		System.out.println("RCOMADM " + RCOMADM);
		System.out.println("MMGR " + MMGR);
		System.out.println("AOM " + AOM);
	}
	
}
