import newsm2.NewSM2;
import variable.PathVariable;

public class Main {
	
	public static void main(String[] args) {
		
		NewSM2 com = new NewSM2(getAssignedPath());
		com.run();
		
//		TestModule test = new TestModule(getAssignedPath());
//		test.run();

		return ;		
	}

	private static PathVariable getAssignedPath() {
		String LORbaseURL = "http://172.31.1.41:55011/LOR/";
		String CMSbaseURL = "http://172.31.1.42:9080/CMS/";
		String pathOffset = "C:\\testdata\\";
		String pathSpecify = "cat";
		
		PathVariable pathVariable = new PathVariable();
		pathVariable.setExcelPath(pathOffset + pathSpecify + ".xls");
		pathVariable.setLogPath(pathOffset + pathSpecify + ".log");
		pathVariable.setLORBaseURL(LORbaseURL);
		pathVariable.setCMSBaseURL(CMSbaseURL);
		
		return pathVariable;
	}
	
}
