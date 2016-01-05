package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.CellTag.sheetType;

public class AddLoanForm extends KeywordsCOM {

	public AddLoanForm(Controller ctrl, sheetType loanType, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		
		switch(loanType){
			case LongTermLoan:
				super.logtab 			= log.LogTag.logtab.LoanFormCOM;
				super.logsubtab 		= log.LogTag.logsubtab.LongTermLoan;
				super.workSheetPath 	= "loanform_com_ltl_" + sheetIndex;
				break;
			case PromissoryNote:
				super.logtab 			= log.LogTag.logtab.LoanFormCOM;
				super.logsubtab 		= log.LogTag.logsubtab.PromissoryNote;
				super.workSheetPath 	= "loanform_com_pn_" + sheetIndex;
				break;
			case BookGarantee:
				super.logtab 			= log.LogTag.logtab.LoanFormCOM;
				super.logsubtab 		= log.LogTag.logsubtab.BookGarantee;
				super.workSheetPath 	= "loanform_com_bg_" + sheetIndex;
				break;
			case OD:
				super.logtab 			= log.LogTag.logtab.LoanFormDDA;
				super.logsubtab 		= log.LogTag.logsubtab.OD;
				super.workSheetPath 	= "loanform_dda_od_" + sheetIndex;
				break;
			default:
				break;		
		}
	}
}
