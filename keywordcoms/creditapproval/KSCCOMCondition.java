package creditapproval;

import base.KeywordsCOM;
import controller.Controller;

public class KSCCOMCondition extends KeywordsCOM{

	public KSCCOMCondition(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.Condition;
		super.logtab 			= log.LogTag.logtab.Condition;
		super.logsubtab 		= log.LogTag.logsubtab.None;

		super.workSheetPath = "CreditApprovalCondition";
	}
	
}
