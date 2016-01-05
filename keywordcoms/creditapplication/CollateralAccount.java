package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class CollateralAccount extends KeywordsCOM {
	
	public CollateralAccount(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.Collateral;
		super.logsubtab 		= log.LogTag.logsubtab.AddAccounting;

		super.workSheetPath = "collateral_addaccounting_" + sheetIndex;
	}
	
}
