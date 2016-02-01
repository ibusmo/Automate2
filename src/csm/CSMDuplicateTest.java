package csm;

import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriverException;

import com.Login;
import com.Logout;
import com.OpenBrowser;
import com.SystemBase;

import controller.Controller;
import customcomponent.InterruptTask;
import variable.PathVariable;

public class CSMDuplicateTest {
	
	private Controller ctrl;
	InterruptTask interrupt;
	String username;
	String url;
	
	public CSMDuplicateTest() {
		url = "https://10.251.108.141:9443/LOR_3_18/";
//		url = "https://10.251.108.141:9443/LOR/";
		username = "AuychaiW";
		
		ctrl = new Controller(getAssignedPath());
	}
	
	public void run() {
		interrupt = new InterruptTask();
		
		try{
		
			openBrowser(SystemBase.LOR);
			login(username, SystemBase.LOR);
			
			register();
			
			logout();
			
			ctrl.disconnect();
		
		}catch (TimeoutException e) {
			System.out.println(username + " -TimeoutException");
		}catch (NoSuchElementException e) {
			System.out.println(username + " -NoSuchElementException");
		}catch (NullPointerException e) {
			System.out.println(username + " -NullPointerException");
		}catch(NoAlertPresentException e){
			System.out.println(username + " -NoAlertPresentException");
		}catch (InvalidElementStateException e){
			System.out.println(username + " -InvalidElementStateException");		
		}catch(UnhandledAlertException e){
			System.out.println(username + " -UnhandledAlertException");	
		}catch(NoSuchWindowException e){
			System.out.println(username + " -NoSuchWindowException");				
		}catch(WebDriverException e){
			System.out.println(username + " -WebDriverException");				
		}
	}
	
	private void register(){
		ctrl.button.linkText("ลงทะเบียน");
		ctrl.dropdown.name("workflowCode", "CSM", "populatePurposeLoanByWorkFlow(this.value,'',true,false,'purposeLoanCode');getWorkflowGroup(this.value);");
		ctrl.checkBox.name("dupApplicationFlg");
		
	//Start capture times
		System.out.println(username + " " + "Start Capture Times");
		long startTime = System.currentTimeMillis();
		ctrl.button.xpath("//*[@id='applicationForm']/div[3]/div/div/div[26]/button[1]");
		ctrl.popup.byxpath("/html/body/form/table/thead/tr/th/div", 2);
	//Hold here for all elements
		int timeCount = 0;
		do {
			try {
				Thread.sleep(10);
				timeCount++;
			} catch (InterruptedException e) {
				System.out.println(username + " " + "Wait, crash !!! " + (timeCount*10) + " ms");
				e.printStackTrace();			
			}
			if (timeCount > 3000) {
				System.out.println(username + " " + "Mait, time out " + (timeCount*10) + " ms");
				break;
			}
		} while (ctrl.jsExecute.forceExeReturn("document.readyState").matches("complete")==false);
	//Stop capture times
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println(username + " " + "Stop Capture Times");
	//Report
		//Subtract a header row
		int numberOfRows = ctrl.verifyData.getSizeByXpath("/html/body/form/table/tbody/tr") - 1;
		String resEstimatedTime = "Estimated Time " + (estimatedTime) + " ms";
		String resNumberOfRows 	= "There are " + (numberOfRows) + " rows";
		System.out.println(username + " **************** " + resEstimatedTime);
		System.out.println(username + " **************** " + resNumberOfRows);
		String resToAlert = username + ", " + resEstimatedTime + ", " + resNumberOfRows;
		ctrl.jsExecute.forceExe("alert('"+resToAlert+"');");
		
		interrupt.waitKey();
		alert();	
		ctrl.button.xpath("/html/body/form/div[2]/button");
		ctrl.popup.byxpath("//*[@id='applicationForm']/div[3]/div/div/div[26]/button[1]", 1);
		
				
		System.out.println(username + " " + "Register");
	}
	
	private PathVariable getAssignedPath() {
		String LORbaseURL = url;
		String CMSbaseURL = "";
		String pathOffset = "C:\\testdata\\";
		String pathSpecify = "cat";
		
		PathVariable pathVariable = new PathVariable();
		pathVariable.setExcelName(pathOffset + pathSpecify + ".xls");
		pathVariable.setLogName(pathOffset + pathSpecify + ".log");
		pathVariable.setLORBaseURL(LORbaseURL);
		pathVariable.setCMSBaseURL(CMSbaseURL);
		
		return pathVariable;
	}
	
	public void openBrowser(SystemBase system) {
		new OpenBrowser(ctrl, system).execute();
	}
	
	public void login(String username, SystemBase system) {
		new Login(ctrl, username, "testuser", system).execute();
		System.out.println(username + " Login");
	}
	
	public void logout() {
		new Logout(ctrl).execute();
		System.out.println(username + " Logout");
	}
	
	public void alert(){
		try{
			ctrl.alertHandle.execute();
		}catch (TimeoutException e){
		}catch(NoAlertPresentException e){
		}catch (InvalidElementStateException e){	
		}
	}
}
