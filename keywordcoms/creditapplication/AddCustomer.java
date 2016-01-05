package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.CellTag.sheetType;

public class AddCustomer extends KeywordsCOM {
	
	public AddCustomer(Controller ctrl, sheetType customerType, int sheetIndex) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.RegCustomer;
		
		switch(customerType){
			case AddNormalCustomer:
				super.logsubtab 		= log.LogTag.logsubtab.NormalCustomer;
				super.workSheetPath 	= "customer_add_" + sheetIndex;
				break;
			case AddLegalCustomer:
				super.logsubtab 		= log.LogTag.logsubtab.LegalCustomer;
				super.workSheetPath 	= "customer_legal_add_" + sheetIndex;
				break;
			default:
				break;
		}
	}
		
}
