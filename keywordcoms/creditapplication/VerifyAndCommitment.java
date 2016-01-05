package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class VerifyAndCommitment extends KeywordsCOM {
	
	public VerifyAndCommitment(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.VerifyCommitment;
		super.logtab 			= log.LogTag.logtab.ExcSummary;
		super.logsubtab 		= log.LogTag.logsubtab.None;
		
		super.workSheetPath = "excutivesummary_" + sheetIndex;
	}
	
}
