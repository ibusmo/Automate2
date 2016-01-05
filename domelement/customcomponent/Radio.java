package customcomponent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controller.EngineController;
import testdata.DataElementObj;
import testdata.CellTag.fieldType;

public class Radio{
	
	WebDriver driver;
	WaitFor waitFor;
	
	public Radio(EngineController comDriver) {
		driver = comDriver.wde.getDriver();
		waitFor = comDriver.waitFor;
	}
	
	public void RunRadio(DataElementObj obj) {
		auto(obj.fieldType, obj.fieldName, obj.fieldValue);
	}
	
	public void auto(fieldType fieldType, String fieldName, String fieldValue) {
		switch(fieldType){
		case id:
			break;
		case name:
			name(fieldName, (int) Math.round(Double.parseDouble(fieldValue)));
			break;
		case xpath:
			break;
		case linktext:
			break;
		default:
			break;
		}
	}
	
	public void name(String field, int value){
		waitFor.name(field);
		List<WebElement> radios = driver.findElements(By.name(field));
		radios.get(--value).click();
	}	
	
	public void id(String field, int value){
		waitFor.id(field);
		List<WebElement> radios = driver.findElements(By.id(field));
		radios.get(--value).click();
	}
	
}
