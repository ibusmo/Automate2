package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.CellTag.sheetType;
import testdata.DataElementObj;

public class CollateralWarranterList extends KeywordsCOM {
	
	String cif;
	
	public CollateralWarranterList(Controller ctrl, sheetType warranterType, String cif) {
		super.ctrl = ctrl;
		this.cif = cif;
		
		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.Collateral;
		
		switch(warranterType){
			case CollNormalWarranter:
				super.logsubtab 		= log.LogTag.logsubtab.AddWarranterNormal;
				super.workSheetPath = "collateral_addwarranter_1";
				break;
			case CollLegalWarranter:
				super.logsubtab 		= log.LogTag.logsubtab.AddWarranterLegal;
				super.workSheetPath = "collateral_addwarranterlegal_1";
				break;
			default:
				break;			
		}
	}

	@Override
	protected boolean preCondition(DataElementObj obj) {
		if(obj.fieldName.contains("filterCIFNo")){
			obj.data = cif;
		}
		return true;
	}
	
	
	
}
