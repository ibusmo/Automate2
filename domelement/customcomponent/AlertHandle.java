package customcomponent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import controller.EngineController;
import testdata.DataElementObj;

public class AlertHandle{

	WebDriver driver;

	public AlertHandle(EngineController comDriver) {
		driver = comDriver.wde.getDriver();
	}
	
	public void RunAlert() {
		Alert javascriptprompt = driver.switchTo().alert();
		javascriptprompt.accept();
	}
	
	public void RunAlert(DataElementObj obj) {
		RunAlert();		
	}
	
	public void execute() {
		Alert javascriptprompt = driver.switchTo().alert();
		javascriptprompt.accept();
	}
	
}
