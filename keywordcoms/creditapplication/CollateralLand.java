package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class CollateralLand extends KeywordsCOM {
		
	public CollateralLand(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 	= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.Collateral;
		super.logsubtab 		= log.LogTag.logsubtab.AddLand;

		super.workSheetPath = "collateral_addland_" + sheetIndex;
	}
	
}
