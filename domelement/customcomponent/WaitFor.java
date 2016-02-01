package customcomponent;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitFor{

	public WebDriverWait wait;
	
	public WaitFor(WebDriverWait webDriverWait) {
		this.wait = webDriverWait;
	}
	
	public void element(By obj){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		}catch(UnreachableBrowserException e){
			
		}
	}
	
	public void id(String id){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		}catch(UnreachableBrowserException e){
			
		}
	}
	public void name(String name){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
		}catch(UnreachableBrowserException e){
			
		}
	}
	public void xpath(String xpath){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		}catch(UnreachableBrowserException e){
			
		}
	}
	public void cssSelector(String cssSelector){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
		}catch(UnreachableBrowserException e){
			
		}
	}
	public void linkText(String linkText){
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
		}catch(UnreachableBrowserException e){
			
		}
	}	
	
}
