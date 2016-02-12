package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class CollateralCheckOrNot extends KeywordsCOM {
	
	public CollateralCheckOrNot(Controller ctrl, Boolean check) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.CollCheck;
		super.logsubtab 		= log.LogTag.logsubtab.Option;
		
		super.workSheetPath = "CallCheck_option_" + (check ? 1 : 0);
	}
	
}
