package customcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import controller.EngineController;
import testdata.DataElementObj;
import testdata.CellTag.fieldType;

public class Dropdown{
	
	WebDriver driver;
	JavascriptExecutor executor;
	WaitFor waitFor;
	
	Type type;
	Button click;
	
	public Dropdown(EngineController comDriver) {
		driver 		= comDriver.wde.getDriver();
		executor 	= comDriver.wde.getExecutor();
		waitFor 	= comDriver.waitFor;
	}
	
	public Dropdown(EngineController comDriver, Type type, Button click) {
		driver 		= comDriver.wde.getDriver();
		executor 	= comDriver.wde.getExecutor();
		waitFor 	= comDriver.waitFor;
		this.type	= type;
		this.click	= click;
	}
	
	public void RunDropdown(DataElementObj obj, boolean notext) {
		auto(obj.fieldType, obj.fieldName, obj.fieldValue,  obj.fieldOptional, obj.data, obj.jsExe, notext);
	}
	
	public void auto(fieldType filedNameType, String selectField, String selectValue, String inputField, String inputValue, String jsExe, boolean noText) {
		switch(filedNameType){
		case id:
			if(jsExe.length()<6 && jsExe.toLowerCase().contains("null")){
				if(noText==true)
					idNoText(selectField, selectValue);
				else
					id(inputField, inputValue, selectField, selectValue);
			}
			else{
				if(noText==true)
					idNoText(selectField, selectValue, jsExe);
				else
					id(inputField, inputValue, selectField, selectValue, jsExe);
			}
			break;
		case name:
			if(jsExe.length()<6 && jsExe.toLowerCase().contains("null")){
				if(noText==true)
					nameNoText(inputValue, selectField, selectValue);
				else
					name(inputField, inputValue, selectField, selectValue);
			}
			else{
				if(noText==true)
					nameNoText(selectField, selectValue, jsExe);
				else
					name(inputField, inputValue, selectField, selectValue, jsExe);
			}
			break;
		case xpath:
			robotByXpath(selectField, (int) Math.round(Double.parseDouble(selectValue)));
		default:
			break;
		}
	}

	public void id(String inputField, String inputValue, String selectField, String selectValue) {
		waitFor.xpath(inputField);
		type.xpath(inputField, inputValue);
		String jQuery = "$('#"+selectField+"').val('"+selectValue+"');";
		executor.executeScript(jQuery, driver.findElement(By.id(selectField)));
	}
	
	public void idNoText(String selectField, String selectValue) {
		String jQuery = "$('#"+selectField+"').val('"+selectValue+"');";
		executor.executeScript(jQuery, driver.findElement(By.id(selectField)));
	}
	
	public void id(String inputField, String inputValue, String selectField, String selectValue, String jsExe) {
		System.out.println(inputField);
		System.out.println(inputValue);
		System.out.println(selectField);
		System.out.println(selectValue);
		System.out.println(jsExe);
		waitFor.xpath(inputField);
		type.xpath(inputField, inputValue);
		String jQuery = "$('#"+selectField+"').val('"+selectValue+"');";
		executor.executeScript(jQuery, driver.findElement(By.id(selectField)));
		executor.executeScript(replaceValue(jsExe, selectValue), driver.findElement(By.id(selectField)));
	}
	
	public void idNoText(String selectField, String selectValue, String jsExe) {
		String jQuery = "$('#"+selectField+"').val('"+selectValue+"');";
		executor.executeScript(jQuery, driver.findElement(By.id(selectField)));
		executor.executeScript(replaceValue(jsExe, selectValue), driver.findElement(By.id(selectField)));
	}
	
	public void idNoText(String selectField, String selectValue, String jsExe, int time) {
		idNoText(selectField, selectValue, jsExe);
		sleep(time);
	}
	
	public void name(String inputField, String inputValue, String selectField, String selectValue) {
		waitFor.xpath(inputField);
		type.xpath(inputField, inputValue);
		String jQuery = "$(\"select[name*='"+selectField+"']\").val( '"+selectValue+"' );";
		executor.executeScript(jQuery, driver.findElement(By.name(selectField)));
	}	
	
	public void nameNoText(String selectField, String selectValue) {
		String jQuery = "$(\"select[name*='"+selectField+"']\").val( '"+selectValue+"' );";
		executor.executeScript(jQuery, driver.findElement(By.name(selectField)));
	}
	
	public void name(String inputField, String inputValue, String selectField, String selectValue, String jsExe) {
		waitFor.xpath(inputField);
		type.xpath(inputField, inputValue);
		String jQuery = "$(\"select[name*='"+selectField+"']\").val( '"+selectValue+"' );";
		executor.executeScript(jQuery, driver.findElement(By.name(selectField)));
		executor.executeScript(replaceValue(jsExe, selectValue), driver.findElement(By.name(selectField)));
	}
	public void nameNoText(String selectField, String selectValue, String jsExe) {
		String jQuery = "$(\"select[name*='"+selectField+"']\").val( '"+selectValue+"' );";
		executor.executeScript(jQuery, driver.findElement(By.name(selectField)));
		executor.executeScript(replaceValue(jsExe, selectValue), driver.findElement(By.name(selectField)));
	}

	public void nameNoText(String selectField, String selectValue, String jsExe, int time) {
		nameNoText(selectField, selectValue, jsExe);
		sleep(time);		
	}

	public void id(String selectField, String selectValue, String jEXE) {
		String jQuery = "$('#"+selectField+"').val('"+selectValue+"');";
		executor.executeScript(jQuery, driver.findElement(By.id(selectField)));
		executor.executeScript(replaceValue(jEXE, selectValue), driver.findElement(By.id(selectField)));		
	}
	
	public void name(String selectField, String selectValue, String jsExe) {
		String jQuery = "$(\"select[name*='"+selectField+"']\").val( '"+selectValue+"' );";
		executor.executeScript(jQuery, driver.findElement(By.name(selectField)));
		executor.executeScript(replaceValue(jsExe, selectValue), driver.findElement(By.name(selectField)));
	}

	public void id(String selectField, String selectValue) {
		String jQuery = "$('#"+selectField+"').val('"+selectValue+"');";
		executor.executeScript(jQuery, driver.findElement(By.id(selectField)));
	}
	
	public void name(String selectField, String selectValue) {
		String jQuery = "$(\"select[name*='"+selectField+"']\").val( '"+selectValue+"' );";
		executor.executeScript(jQuery, driver.findElement(By.name(selectField)));
	}
	
	private String replaceValue(String str, String val){
		str = str.replaceAll("this.value", "'"+val+"'");
		str = str.replaceAll("this", val);
		return str;
	}
	
	//Robot
	
	public void robotByName(String selectField, int selectIndex){
		waitFor.name(selectField);
		click.name(selectField);
		selectIndex++;
		for(int i=0;i<selectIndex;i++){
			driver.findElement(By.name(selectField)).sendKeys(Keys.DOWN);
		}		
		driver.findElement(By.name(selectField)).sendKeys(Keys.ENTER);
	}
	
	public void robotById(String selectField, int selectIndex){
		waitFor.id(selectField);
		click.id(selectField);
		selectIndex++;
		for(int i=0;i<selectIndex;i++){
			driver.findElement(By.id(selectField)).sendKeys(Keys.DOWN);
		}
		driver.findElement(By.id(selectField)).sendKeys(Keys.ENTER);		
	}
	
	public void robotByXpath(String selectField, int selectIndex){
		
//		System.out.println(selectField);
//		System.out.println(selectIndex);
		
		waitFor.xpath(selectField);
		click.xpath(selectField);
		driver.findElement(By.xpath(selectField)).clear();
		selectIndex++;
		for(int i=0;i<selectIndex;i++){
			driver.findElement(By.xpath(selectField)).sendKeys(Keys.DOWN);
		}		
		driver.findElement(By.xpath(selectField)).sendKeys(Keys.ENTER);
	}
	
	public void robotSelectId(String selectField, int selectIndex){
		
		String xpathRelative = "//*[@id='"+selectField+"']/../input[1]";
		
//		System.out.println(selectField);
//		System.out.println(xpathRelative);
		
		waitFor.xpath(xpathRelative);
		click.xpath(xpathRelative);
		driver.findElement(By.xpath(xpathRelative)).clear();
		selectIndex++;
		for(int i=0;i<selectIndex;i++){
			driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.DOWN);
		}		
		driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.ENTER);
	}
	
	public void robotSelectId(String selectField, int selectIndex, int idx){
		
		String xpathRelative = "//*[@id='"+selectField+"']/../input[" + idx + "]";
		
//		System.out.println(selectField);
//		System.out.println(xpathRelative);
		
		waitFor.xpath(xpathRelative);
		click.xpath(xpathRelative);
		driver.findElement(By.xpath(xpathRelative)).clear();
		selectIndex++;
		for(int i=0;i<selectIndex;i++){
			driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.DOWN);
		}		
		driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.ENTER);
	}
	
	public void robotSelectName(String selectField, int selectIndex){
		
		String xpathRelative = "//*[@name='"+selectField+"']/../input[1]";
		
//		System.out.println(selectField);
//		System.out.println(xpathRelative);
		
		waitFor.xpath(xpathRelative);
		click.xpath(xpathRelative);
		driver.findElement(By.xpath(xpathRelative)).clear();
		selectIndex++;
		for(int i=0;i<selectIndex;i++){
			driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.DOWN);
		}		
		driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.ENTER);
	}
	
	public void robotSelectName(String selectField, int selectIndex, int idx){
		
		String xpathRelative = "//*[@name='"+selectField+"']/../input[" + idx + "]";
		
//		System.out.println(selectField);
//		System.out.println(xpathRelative);
		
		waitFor.xpath(xpathRelative);
		click.xpath(xpathRelative);
		driver.findElement(By.xpath(xpathRelative)).clear();
		selectIndex++;
		for(int i=0;i<selectIndex;i++){
			driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.DOWN);
		}		
		driver.findElement(By.xpath(xpathRelative)).sendKeys(Keys.ENTER);
	}	
	
	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
