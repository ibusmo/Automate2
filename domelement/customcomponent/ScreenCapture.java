package customcomponent;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenCapture {
	
	private WebDriver driver;
	private File imageFile;
	
	public ScreenCapture(WebDriver driver){
		this.driver = driver;
	}

	public boolean saveShotImage(String path){
		shot();
		return saveImage(path);
	}
	
	public File getShotImage(){
		shot();
		return getImage();
	}
	
	public boolean saveImage(String path){
		try {
			FileUtils.copyFile(imageFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public File getImage(){
		return imageFile;		
	}
	
	public void shot(){
		imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	}
	
}
