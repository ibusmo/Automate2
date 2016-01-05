package customcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import controller.EngineController;
import testdata.DataElementObj;
import testdata.CellTag.fieldType;

public class Type{
	
	WebDriver driver;
	WaitFor waitFor;
	
	public Type(EngineController comDriver) {
		driver = comDriver.wde.getDriver();
		waitFor = comDriver.waitFor;
	}
	
	public void RunText(DataElementObj obj) {
		auto(obj.fieldType, obj.fieldName, obj.data);
	}
	
	public void auto(fieldType filedType, String field, String value) {
		switch(filedType){
		case id:
			id(field, value);
			break;
		case name:
			name(field, value);
			break;
		case xpath:
			xpath(field, value);
			break;
		case linktext:
			linkText(field, value);
			break;
		default:
			break;
		}
	}
	
	public void id(String field, String value){
		waitFor.id(field);
		driver.findElement(By.id(field)).clear();
		driver.findElement(By.id(field)).sendKeys(value);
	}

	public void name(String field, String value){
		waitFor.name(field);
		driver.findElement(By.name(field)).clear();
		driver.findElement(By.name(field)).sendKeys(value);
	}

	public void linkText(String field, String value){
		waitFor.linkText(field);
		driver.findElement(By.linkText(field)).clear();
		driver.findElement(By.linkText(field)).sendKeys(value);
	}

	public void xpath(String field, String value){
		waitFor.xpath(field);
		driver.findElement(By.xpath(field)).clear();
		driver.findElement(By.xpath(field)).sendKeys(value);
	}
	
}
