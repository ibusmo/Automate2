package creditapproval;

import base.KeywordsCOM;
import controller.Controller;
import testdata.CellTag.Commitment;

public class BCOMRecordPrintCommitment extends KeywordsCOM{

	public BCOMRecordPrintCommitment(Controller ctrl, Commitment creditCommitment) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.Comment;
		super.logtab 			= log.LogTag.logtab.Committment;
		super.logsubtab 		= log.LogTag.logsubtab.None;
		
		int idx = 1;
		switch(creditCommitment){
		case NOTPASS:
			idx = 0;
			break;
		case PASS:
			idx = 1;
			break;
		case REQ_PASS:
		case REQ_NOTPASS:
			idx = 2;
			break;
		default:
			break;
		}

		super.workSheetPath = "CreditApproveCommitment_" + idx;
	}
	
}
