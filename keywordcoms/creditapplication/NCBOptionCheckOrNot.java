package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class NCBOptionCheckOrNot extends KeywordsCOM {

	public NCBOptionCheckOrNot(Controller ctrl, Boolean NCB) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.NCB;
		super.logsubtab 		= log.LogTag.logsubtab.Option;
		
		super.workSheetPath = "NCB_option_" + (NCB ? 1 : 0);
	}

}
