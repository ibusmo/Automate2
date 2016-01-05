package contract;

import base.KeywordsCOM;
import controller.Controller;

public class PrepContractVerify extends KeywordsCOM{

	public PrepContractVerify(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.ContractVerify;
		super.logtab 			= log.LogTag.logtab.ContractVerify;
		super.logsubtab 		= log.LogTag.logsubtab.None;

		super.workSheetPath = "ContractPrepVerify";
	}
	
}