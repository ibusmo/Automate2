package testmodule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDrive {
	
	public void run(){
		WebDriver driverFF = new FirefoxDriver();
		
		WebDriverWait driverWait = new WebDriverWait(driverFF, 30);
		
	}
	
}
