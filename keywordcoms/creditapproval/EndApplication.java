
package creditapproval;
import base.KeywordsCOM;
import controller.Controller;

public class EndApplication extends KeywordsCOM {
	
	public EndApplication(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.Comment;
		super.logtab 			= log.LogTag.logtab.CustomerNotify;
		super.logsubtab 		= log.LogTag.logsubtab.None;	
		
		super.workSheetPath = "";
	}
	
}
