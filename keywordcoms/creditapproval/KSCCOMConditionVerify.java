package creditapproval;

import base.KeywordsCOM;
import controller.Controller;

public class KSCCOMConditionVerify extends KeywordsCOM{

	public KSCCOMConditionVerify(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.ConditionVerify;
		super.logtab 			= log.LogTag.logtab.ConditionVerify;
		super.logsubtab 		= log.LogTag.logsubtab.None;

		super.workSheetPath = "CreditApprovalVerify";
	}
	
}
