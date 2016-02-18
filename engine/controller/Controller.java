package controller;

import org.openqa.selenium.WebDriver;

import customcomponent.AlertHandle;
import customcomponent.CheckBox;
import customcomponent.Button;
import customcomponent.DatePicker;
import customcomponent.Dropdown;
import customcomponent.VerifyData;
import customcomponent.WaitFor;
import log.LogCat;
import customcomponent.JSExecute;
import customcomponent.Popup;
import customcomponent.Radio;
import customcomponent.ScreenCapture;
import customcomponent.Type;
import testdata.TestConfig;
import variable.PathVariable;

public class Controller {
	
	private EngineController commonDriver;
	public WebDriver driver;	
	public LogCat logCat;	
	public PathVariable pathVariable;
	
	public TestConfig testConfig;

	public WaitFor waitFor;
	
	public AlertHandle alertHandle;
	public CheckBox checkBox;
	public Button button;
	public DatePicker datePicker;
	public Dropdown dropdown;
	public VerifyData verifyData;
	public JSExecute jsExecute;
	public Radio radio;
	public Popup popup;
	public Type type;
	
	public ScreenCapture screenCapture;
	public int imgId;

	public Controller(PathVariable pathVariable){
		this.pathVariable 	= pathVariable;
		
		initIO();
		initDriver();
		initDOM();
		initTestConfig();
	}
	
	public Controller(PathVariable pathVariable, String bw){
		this.pathVariable 	= pathVariable;
		
		initIO();
		initDriver(bw);
		initDOM();
		initTestConfig();
	}
	
	public Controller(String LORBaseURL, String CMSBaseURL, String pathOffset, String pathSpecify){
		pathVariable = new PathVariable();
		pathVariable.setExcelName(pathOffset + pathSpecify + ".xlsx");
		pathVariable.setLogName(pathOffset + pathSpecify + ".log");
		pathVariable.setLORBaseURL("http://172.31.1.41:55011/LOR/");
		pathVariable.setCMSBaseURL("http://172.31.1.42:9080/CMS/");
		
		initIO();
		initDriver();
		initDOM();
		initTestConfig();
	}
	
	private void initIO() {
		logCat = new LogCat(pathVariable.getRelativeLogPath());
	}
	
	public void initDriver(){
		commonDriver	= new EngineController();
		driver 			= commonDriver.wde.getDriver();
		waitFor			= commonDriver.waitFor;
	}
	
	public void initDriver(String bw){
		commonDriver	= new EngineController(bw);
		driver 			= commonDriver.wde.getDriver();
		waitFor			= commonDriver.waitFor;
	}
	
	public void initDOM(){
		alertHandle = new AlertHandle(commonDriver);
		checkBox 	= new CheckBox(commonDriver);
		button 		= new Button(commonDriver);
		datePicker 	= new DatePicker(commonDriver);
		verifyData 	= new VerifyData(commonDriver);
		jsExecute 	= new JSExecute(commonDriver);
		radio 		= new Radio(commonDriver);
		popup		= new Popup(commonDriver, logCat);
		type 		= new Type(commonDriver);
		dropdown 	= new Dropdown(commonDriver, type, button);
		
		screenCapture = new ScreenCapture(driver);
		imgId = 0;
	}
	
	public void initTestConfig(){
		testConfig	= new TestConfig(pathVariable.getRelativeExcelPath(), logCat);
	}
	
	public void disconnect(){
		commonDriver.wde.disconnected();
	}
	
}
