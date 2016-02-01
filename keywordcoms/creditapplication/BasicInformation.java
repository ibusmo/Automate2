package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.DataElementObj;
import testdata.CellTag.inputType;

public class BasicInformation extends KeywordsCOM {
	
	public BasicInformation(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.BasiInfo;
		super.logtab 			= log.LogTag.logtab.ExcSummary;
		super.logsubtab 		= log.LogTag.logsubtab.None;
		
		super.workSheetPath = "excutivesummary_" + sheetIndex;
	}

	@Override
	protected boolean preCondition(DataElementObj obj) {
		// ปุ่ม Credit score
		if(obj.type==inputType.button){
			if(obj.data.toLowerCase().contains("credit score")){
				//here
				obj.run = true;
				System.out.println(obj.data + "********************** set RUN to " + obj.run);
			}
		}
		return true;
	}
	
}
