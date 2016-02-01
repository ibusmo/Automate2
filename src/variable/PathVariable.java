package variable;

import java.io.File;
import java.time.LocalDateTime;

public class PathVariable {

	private String browser;
	
	private String LORBaseURL = null;
	private String CMSBaseURL = null;
	
	private String logName = null;
	private String logType = null;
	
	private String excelName = null;
	private String excelType = null;
	
	private String specify = null;

	private String offset = null;
	private String directory = null;

	private String timeStamp = null;

	private String relativeExcelPath = null;
	private String relativeLogPath = null;
	private String relativeLog = null;
	
	public PathVariable(){
		setTimeStamp();
	}	
	
	public String getLogName() {
		return logName;
	}

	public void setLogName(String logPath) {
		this.logName = logPath;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelPath) {
		this.excelName = excelPath;
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

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getExcelType() {
		return excelType;
	}

	public void setExcelType(String excelType) {
		this.excelType = excelType;
	}

	public String getOffsetPath() {
		return offset;
	}

	public void setOffsetPath(String offsetPath) {
		this.offset = offsetPath;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
		File file = new File(offset + "\\" + directory);
		if (!file.exists()) {
			System.out.println("Directory is not exists");
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}else{
			System.out.println("Directory is exists");			
		}
		setRelativeExcelPath(offset + "\\" + excelName + excelType);
		setRelativeLogPath(offset + "\\" + directory + "\\" + logName + getTimeStamp() + logType);
		setRelativeLog(offset + "\\" + directory + "\\" + logName + getTimeStamp());
		System.out.println(getRelativeExcelPath());
		System.out.println(getRelativeLogPath());
		System.out.println(getRelativeLog());
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void setTimeStamp() {
		timeStamp = "_" + getCurDate() + "_" + getCurTime();
	}
	
	private String getCurDate() {
		String D = String.format("%02d", LocalDateTime.now().getDayOfMonth());
		String M = String.format("%02d", LocalDateTime.now().getMonthValue());
		int Y = LocalDateTime.now().getYear();
		String localTime = Y + "" + M + "" + D;
		return localTime;
	}	
	
	private String getCurTime() {
		String H = String.format("%02d", LocalDateTime.now().getHour());
		String M = String.format("%02d", LocalDateTime.now().getMinute());
		String S = String.format("%02d", LocalDateTime.now().getSecond());
		String localTime = H + "" + M + "" + S;
		return localTime;
	}

	public String getSpecify() {
		return specify;
	}

	public void setSpecify(String pathSpecify) {
		this.specify = pathSpecify;
		setExcelName(pathSpecify);
		setLogName(pathSpecify);
		setDirectory(pathSpecify);
	}

	public String getRelativeExcelPath() {
		return relativeExcelPath;
	}

	public void setRelativeExcelPath(String relativeExcelPath) {
		this.relativeExcelPath = relativeExcelPath;
	}

	public String getRelativeLogPath() {
		return relativeLogPath;
	}

	public void setRelativeLogPath(String relativeLogPath) {
		this.relativeLogPath = relativeLogPath;
	}

	public String getRelativeLog() {
		return relativeLog;
	}

	public void setRelativeLog(String relativeLog) {
		this.relativeLog = relativeLog;
	}
}
