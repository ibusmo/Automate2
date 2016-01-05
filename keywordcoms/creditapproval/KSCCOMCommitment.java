package creditapproval;

import base.KeywordsCOM;
import controller.Controller;
import testdata.CellTag.Commitment;

public class KSCCOMCommitment extends KeywordsCOM{

	public KSCCOMCommitment(Controller ctrl, Commitment creditCommitment) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.Comment;
		super.logtab 			= log.LogTag.logtab.Committment;
		super.logsubtab 		= log.LogTag.logsubtab.None;

		super.workSheetPath = "CreditApproveCommitment_" + (creditCommitment.equals(Commitment.PASS) ? 1 : 0);
	}
	
}
