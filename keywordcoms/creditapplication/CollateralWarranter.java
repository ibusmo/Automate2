package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.CellTag.sheetType;

public class CollateralWarranter extends KeywordsCOM {
	
	//Not use
	int collacteralIndex;
	
	public CollateralWarranter(Controller ctrl, sheetType warranterType, int sheetIndex, int collacteralIndex) {
		super.ctrl = ctrl;
		this.collacteralIndex = collacteralIndex;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.Collateral;
		
		switch(warranterType){
			case CollNormalWarranter:
				super.logsubtab 		= log.LogTag.logsubtab.AddWarranterNormal;
				super.workSheetPath = "collateral_addwarranter_" + sheetIndex;
				break;
			case CollLegalWarranter:
				super.logsubtab 		= log.LogTag.logsubtab.AddWarranterLegal;
				super.workSheetPath = "collateral_addwarranterlegal_" + sheetIndex;
				break;
			default:
				break;			
		}
	}
	
}
