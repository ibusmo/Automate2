package customcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import controller.EngineController;
import testdata.DataElementObj;

public class JSExecute{
	
	WebDriver driver;
	WaitFor waitFor;

	JavascriptExecutor executor;
	String targetExe;
	int sleepTime;
	
	public JSExecute(EngineController comDriver) {
		driver 		= comDriver.wde.getDriver();
		executor 	= comDriver.wde.getExecutor();
		waitFor  	= comDriver.waitFor;
		targetExe = "/html/body";
		sleepTime = 500;
	}
	
	public void runExe(DataElementObj obj){
		waitFor.xpath(targetExe);
		executor.executeScript(obj.fieldName, driver.findElement(By.xpath(targetExe)));
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void forceExe(String jsExe){
		waitFor.xpath(targetExe);
		executor.executeScript(jsExe, driver.findElement(By.xpath(targetExe)));
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String forceExeReturn(String jsExe){
		waitFor.xpath(targetExe);
		return (String)executor.executeScript("return " + jsExe, driver.findElement(By.xpath(targetExe)));
	}
	
	public void id(String target, String jQuery, int time){
		waitFor.id(target);
		executor.executeScript(jQuery, driver.findElement(By.id(target)));
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void name(String target, String jQuery, int time){
		waitFor.name(target);
		executor.executeScript(jQuery, driver.findElement(By.name(target)));
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void xpath(String target, String jQuery, int time){
		waitFor.xpath(target);
		executor.executeScript(jQuery, driver.findElement(By.xpath(target)));
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
