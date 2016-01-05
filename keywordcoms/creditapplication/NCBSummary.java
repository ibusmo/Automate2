package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.DataElementObj;
import testdata.CellTag.inputType;

public class NCBSummary extends KeywordsCOM {
	
	int collacteralIndex;
	
	public NCBSummary(Controller ctrl, int sheetIndex, int collacteralIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.BasiInfo;
		super.logtab 			= log.LogTag.logtab.NCB;
		super.logsubtab 		= log.LogTag.logsubtab.None;
		
		super.workSheetPath = "NCB_summary_" + sheetIndex;
		this.collacteralIndex = collacteralIndex;
	}
	
	@Override
	protected boolean preCondition(DataElementObj obj) {
		if(obj.type==inputType.radio){
			if(obj.data.contains("CIF")){
				int radioPosition = collacteralIndex;
				obj.fieldValue = radioPosition + "";
			}
		}
		return true;
	}
	
}
