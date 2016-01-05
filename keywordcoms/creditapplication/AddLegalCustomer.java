package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class AddLegalCustomer extends KeywordsCOM {
	
	public AddLegalCustomer(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;		

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.RegLegalCustomer;
		super.logsubtab 		= log.LogTag.logsubtab.Add;
		
		super.workSheetPath = "customer_org_add_" + sheetIndex;
	}
	
}
