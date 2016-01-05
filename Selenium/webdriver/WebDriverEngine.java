package webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverEngine {
	
	private WebDriver driver = null;
	private WebDriverWait driverWait = null;
	private JavascriptExecutor executor = null;
	private String webEngine = null;

	//default WebDriver
	public WebDriverEngine(){
			webEngine = "firefox";
			setDriver();
			setDriverWait();
			setExecutor();
	}

	//custom WebDriver
	public WebDriverEngine(String browser){
			webEngine = browser;
			setDriver();
			setDriverWait();
			setExecutor();
	}

	public void setDriver() {
		if(webEngine == "firefox"){
			driver = new FirefoxDriver();
		}
		else if(webEngine == "IE"){
			System.setProperty("webdriver.ie.driver", "C://Devs//IE//32//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else{
			driver = new FirefoxDriver();
		}
	}
	
	public void setDriverWait(){
		driverWait = new WebDriverWait(driver, 30);
	}	
	
	public void setExecutor(){
		executor =  (JavascriptExecutor) driver;
	}
	
	public void Close(){
		driver.close();
	}

	public void quit() {
		driver.quit();
	}
	
	public void disconnected() {
		driver.close();
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getDriverWait() {
		return driverWait;
	}

	public JavascriptExecutor getExecutor() {
		return executor;
	}
	
}
