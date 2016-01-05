package creditanalysis;
import base.KeywordsCOM;
import controller.Controller;

public class SecCAComment extends KeywordsCOM {
	
	public SecCAComment(Controller ctrl) {
		super.ctrl = ctrl;
		
		super.logoperation 		= log.LogTag.logoperation.Comment;
		super.logtab 			= log.LogTag.logtab.CA;
		super.logsubtab 		= log.LogTag.logsubtab.info;

		super.workSheetPath = "CAComment";
	}
	
}
