package testmodule;

import base.KeywordsCOM;
import controller.Controller;
import customcomponent.InterruptTask;
import newsm2.BaseApplication;
import newsm2.COMAutoElementConfig;
import variable.PathVariable;

public class DataDrive extends BaseApplication {

	InterruptTask interrupt = new InterruptTask();	
	String sheetname;
	
	public DataDrive(PathVariable pathVariable, String sheetname) {
		this.ctrl = new Controller(pathVariable);
		this.caeConfig = new COMAutoElementConfig(true);
		this.sheetname = sheetname;
	}

	public void run(){		
//		openBrowser(SystemBase.LOR);
		
		new DataInput(ctrl, sheetname).execute();		
		
		interrupt.waitKey();
		ctrl.disconnect();
	}
	
	public class DataInput extends KeywordsCOM {
		
		public DataInput(Controller ctrl, String sheetname) {
			super.ctrl = ctrl;

			super.logoperation 		= log.LogTag.logoperation.DataDrive;
			super.logtab 			= log.LogTag.logtab.DataDrive;
			super.logsubtab 		= log.LogTag.logsubtab.DataDrive;

			super.workSheetPath = sheetname;
		}
		
	}
}
