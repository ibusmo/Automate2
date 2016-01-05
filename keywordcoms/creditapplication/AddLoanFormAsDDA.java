package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class AddLoanFormAsDDA extends KeywordsCOM {

	public AddLoanFormAsDDA(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.LoanFormDDA;
		super.logsubtab 		= log.LogTag.logsubtab.Add;

		super.workSheetPath = "loanform_dda__add_" + sheetIndex;
	}
	
}
