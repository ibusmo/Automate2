package creditapplication;

import base.KeywordsCOM;
import controller.Controller;

public class BasicInformation extends KeywordsCOM {
	
	public BasicInformation(Controller ctrl, int sheetIndex) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.BasiInfo;
		super.logtab 			= log.LogTag.logtab.ExcSummary;
		super.logsubtab 		= log.LogTag.logsubtab.None;
		
		super.workSheetPath = "excutivesummary_" + sheetIndex;
	}
	
}
