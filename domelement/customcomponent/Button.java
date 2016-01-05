package customcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import controller.EngineController;
import testdata.DataElementObj;
import testdata.CellTag.fieldType;

public class Button {

	WebDriver driver;
	WaitFor WaitFor;

	JavascriptExecutor executor;
	String targetExe;
	int sleepTime;

	public Button(EngineController comDriver) {
		driver = comDriver.wde.getDriver();
		executor = comDriver.wde.getExecutor();
		WaitFor = comDriver.waitFor;
		targetExe = "/html/body";
		sleepTime = 500;
	}

	public void RunButton(DataElementObj obj) {
		auto(obj.fieldType, obj.fieldName);
	}

	public void auto(fieldType filedType, String fieldName) {
		switch (filedType) {
		case id:
			id(fieldName);
			break;
		case name:
			name(fieldName);
			break;
		case xpath:
			xpath(fieldName);
			break;
		case linktext:
			linkText(fieldName);
			break;
		default:
			break;
		}
	}

	public void id(String field) {
		WaitFor.id(field);
		driver.findElement(By.id(field)).click();
	}

	public void name(String field) {
		WaitFor.name(field);
		driver.findElement(By.name(field)).click();
	}

	public void linkText(String field) {
		WaitFor.linkText(field);
		driver.findElement(By.linkText(field)).click();
	}

	public void xpath(String field) {
		WaitFor.xpath(field);
		driver.findElement(By.xpath(field)).click();
	}

	public void save(DataElementObj obj) {
		String jQuery = "";
		switch (obj.type) {
		case save:
			if (obj.data.contains("register")) {
				jQuery = "doSave();";
			} else {
				jQuery = "submitForm('doSaveExecute');";
			}
			break;
		case savedraft:
			jQuery = "submitForm('doSaveDraftExecute');";
			break;
		default:
			break;
		}
		exe(jQuery);
	}

	private void exe(String jQuery) {
		WaitFor.xpath(targetExe);
		executor.executeScript(jQuery, driver.findElement(By.xpath(targetExe)));
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
