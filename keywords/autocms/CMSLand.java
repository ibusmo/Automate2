package autocms;

import controller.Controller;

public class CMSLand extends CMSBase{
	
	public CMSLand(Controller ctrl){
		super.ctrl = ctrl;		

		super.logoperation 		= log.LogTag.logoperation.CMS;
		super.logtab 			= log.LogTag.logtab.Land;
		super.logsubtab 		= log.LogTag.logsubtab.Evaluate;
		
		super.workSheetPath = "cms_land";
	}
	
}
