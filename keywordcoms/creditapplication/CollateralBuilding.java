package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.DataElementObj;
import testdata.CellTag.inputType;

public class CollateralBuilding extends KeywordsCOM {
	
	int collacteralIndex;
	
	public CollateralBuilding(Controller ctrl, int sheetIndex, int collacteralIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.Collateral;
		super.logsubtab 		= log.LogTag.logsubtab.AddBuilding;

		super.workSheetPath = "collateral_addbuilding_" + sheetIndex;
		this.collacteralIndex = collacteralIndex;
	}
	
	@Override
	protected boolean preCondition(DataElementObj obj) {
		if(obj.type==inputType.radio){
			if(obj.data.contains("เลขที่เอกสารสิทธิ์")){
				int radioPosition = collacteralIndex;
				obj.fieldValue = radioPosition + "";
				System.out.println("radioPosition = " + radioPosition);
			}
		}
		else if(obj.type==inputType.button){
			if(obj.data.contains("ดูรายละเอียด")){
				int xpathPosition = collacteralIndex + 1;
				obj.fieldName = obj.fieldName.replaceAll("position", xpathPosition+"");
				System.out.println("xpathPosition = " + xpathPosition);
			}
		}
		return true;
	}
	
}
