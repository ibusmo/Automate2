import newsm2.NewSM2;
import testmodule.DataDrive;
import testmodule.TestDrive;

import java.time.LocalDateTime;

import csm.*;
import variable.PathVariable;

@SuppressWarnings("unused")
public class Main {
	
	public static void main(String[] args) {
		
		new NewSM2(getAssignedPath()).run();
		
		return ;		
	}
	
	private static PathVariable getAssignedPath() {
		
		PathVariable pathVariable = new PathVariable();
		pathVariable.setLORBaseURL("http://172.31.1.41:55011/LOR/");
		pathVariable.setCMSBaseURL("http://172.31.1.42:9080/CMS/");
		pathVariable.setOffsetPath("C:\\testdata\\LOR2SIT2");
		pathVariable.setExcelType(".xls");
		pathVariable.setLogType(".log");
		
		pathVariable.setSpecify("new");
		
		return pathVariable;
	}
	
}
