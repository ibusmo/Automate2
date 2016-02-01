package testmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import customcomponent.InterruptTask;

public class TestDrive {	
	
	InterruptTask interrupt = new InterruptTask();
	
	public void run(){
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://localhost/nodejs/Dummy/UITestDemo/index");
		driver.findElement(By.name("username")).sendKeys("SuperMan");
		driver.findElement(By.name("password")).sendKeys("testuser");
		
		interrupt.waitKey();
		driver.findElement(By.name("commit")).click();
		
		interrupt.waitKey();

		driver.close();
		driver.quit();
	}
	
}
