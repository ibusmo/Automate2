package creditanalysis;

import base.KeywordsCOM;
import controller.Controller;
import testdata.DataElementObj;
import testdata.CellTag.inputType;

public class BranchCA extends KeywordsCOM {
	
	public BranchCA(Controller ctrl) {
		super.ctrl = ctrl;

		super.logoperation 		= log.LogTag.logoperation.CA;
		super.logtab 			= log.LogTag.logtab.CA;
		super.logsubtab 		= log.LogTag.logsubtab.info;
		
		super.workSheetPath = "CA";
	}
	
	@Override
	protected boolean preCondition(DataElementObj obj) {
		
		// ปุ่ม CA แบบย่อ -แบบเต็ม
		if(obj.type==inputType.button){
			if(obj.data.contains("บันทึก")){
				String tmp = ctrl.verifyData.getTextByXpath(obj.fieldName);
				System.out.println(tmp);
				obj.run = tmp.contains("บันทึก") ? true : false;
			}
		}
		
		// ปุ่ม Credit score
		if(obj.type==inputType.button){
			if(obj.data.toLowerCase().contains("credit score")){
				//here
				obj.run = false;
				System.out.println(obj.data + "********************** set RUN to " + obj.run);
			}
		}
		
		return true;
	}
}
