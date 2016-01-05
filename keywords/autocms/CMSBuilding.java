package autocms;

import controller.Controller;

public class CMSBuilding extends CMSBase{
	
	public CMSBuilding(Controller ctrl){
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.CMS;
		super.logtab 			= log.LogTag.logtab.Building;
		super.logsubtab 		= log.LogTag.logsubtab.Evaluate;
		
		super.workSheetPath = "cms_building";
	}
	
}
