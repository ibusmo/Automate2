package customcomponent;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controller.EngineController;
import testdata.DataElementObj;
import testdata.CellTag.fieldType;

public class CheckBox {
	
	private WebDriver driver;
	private WaitFor waitFor;
	
	public CheckBox(EngineController comDriver) {
		driver = comDriver.wde.getDriver();
		waitFor = comDriver.waitFor;
	}

	public void RunCheckBox(DataElementObj obj) {
		auto(obj.fieldType, obj.fieldName);
	}
	
	public void auto(fieldType filedType, String fieldName) {
		switch(filedType){
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
	
	public void id(String field){
		waitFor.id(field);
		boolean isChecked = driver.findElement(By.id(field)).getAttribute("checked")==null ? false : true;
		if(!isChecked) driver.findElement(By.id(field)).click();
	}

	public void name(String field){
		waitFor.name(field);
		boolean isChecked = driver.findElement(By.name(field)).getAttribute("checked")==null ? false : true;
		if(!isChecked) driver.findElement(By.name(field)).click();
	}

	public void linkText(String field){
		waitFor.linkText(field);
		boolean isChecked = driver.findElement(By.linkText(field)).getAttribute("checked")==null ? false : true;
		if(!isChecked) driver.findElement(By.linkText(field)).click();
	}

	public void xpath(String field){
		waitFor.xpath(field);
		boolean isChecked = driver.findElement(By.xpath(field)).getAttribute("checked")==null ? false : true;
		if(!isChecked) driver.findElement(By.xpath(field)).click();
	}
	

	public void name(String field, int value){
		--value;
		waitFor.name(field);
		List<WebElement> checkboxs = driver.findElements(By.name(field));
		boolean isChecked = checkboxs.get(value).getAttribute("checked")==null ? false : true;
		if(!isChecked) checkboxs.get(value).click();
	}
//	String isChecked = "false";
//	try{
//		isChecked = driver.findElement(By.name(checkBoxAssign)).getAttribute("checked");
//		if(isChecked==null)	isChecked = "false";
//
//		if(!isChecked.matches("true")){
//			driver.findElement(By.name(checkBoxAssign)).click();
//		}
//		
//		//System.out.println(isChecked);			
//	}catch(NullPointerException e){
//		isChecked = "false";
//	}	
	
}
