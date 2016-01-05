package variable;


public class PathVariable {

	private String browser;
	
	private String LORBaseURL;
	private String CMSBaseURL;
	
	private String logPath = null;
	private String excelPath = null;
	
	public PathVariable(){
		
	}	
	
	public PathVariable(String log, String excel){
		setLogPath(log);
		setExcelPath(excel);
	}

	public String getLogPath() {
		return logPath;
	}

	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}

	public String getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

	public String getLORBaseURL() {
		return LORBaseURL;
	}

	public void setLORBaseURL(String lORBaseURL) {
		this.LORBaseURL = lORBaseURL;
	}

	public String getCMSBaseURL() {
		return CMSBaseURL;
	}

	public void setCMSBaseURL(String cMSBaseURL) {
		this.CMSBaseURL = cMSBaseURL;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
