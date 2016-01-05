package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class CollateralLottery extends KeywordsCOM {
	
	public CollateralLottery(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.Collateral;
		super.logsubtab 		= log.LogTag.logsubtab.AddLottery;

		super.workSheetPath = "collateral_addlottery_" + sheetIndex;
	}
	
}