package creditanalysis;

import base.KeywordsCOM;
import controller.Controller;
import testdata.DataElementObj;
import testdata.CellTag.inputType;

public class SecCA extends KeywordsCOM {
	
	public SecCA(Controller ctrl) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.CA;
		super.logtab 			= log.LogTag.logtab.CA;
		super.logsubtab 		= log.LogTag.logsubtab.info;
		
		super.workSheetPath = "CA";
	}
	
	@Override
	protected boolean preCondition(DataElementObj obj) {
		if(obj.type==inputType.button){
			if(obj.data.contains("บันทึก")){
				String tmp = ctrl.verifyData.getTextByXpath(obj.fieldName);
				System.out.println(tmp);
				obj.run = tmp.contains("บันทึก") ? true : false;
			}
		}
		return true;
	}
}
