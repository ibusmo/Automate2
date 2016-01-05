package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class CollateralLegalWarranter extends KeywordsCOM {
	
	//Not use
	int collacteralIndex;
	
	public CollateralLegalWarranter(Controller ctrl, int sheetIndex, int collacteralIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.Collateral;
		super.logsubtab 		= log.LogTag.logsubtab.AddWarranterLegal;

		super.workSheetPath = "collateral_addwarranterlegal_" + sheetIndex;
		this.collacteralIndex = collacteralIndex;
	}
	
}
