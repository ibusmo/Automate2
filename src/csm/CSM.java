package csm;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;

import com.AttachFiles;
import com.Login;
import com.Logout;
import com.OpenBrowser;
import com.SystemBase;

import controller.Controller;
import customcomponent.InterruptTask;
import variable.PathVariable;

public class CSM {

	private Controller ctrl;
	InterruptTask interrupt;
	String username;
	String cif;
	
	public CSM(PathVariable pathVariable, String username, String cif) {
		ctrl = new Controller(pathVariable);
		this.username = username;
		this.cif = cif;
	}
	
	public void run() {
			interrupt = new InterruptTask();
			
			try{
			
			openBrowser(SystemBase.LOR);
			login(username, SystemBase.LOR);
			System.out.println(username + " " + cif + " " + "Login");
			
			register();
			customer();
			loanApp();
			document();
			policy();
			sendwork();
			verify();
			
			logout();
			
			ctrl.disconnect();
			
			}catch (TimeoutException e) {
				System.out.println(username + " " + cif + " " + "-TimeoutException");
			}catch (NoSuchElementException e) {
				System.out.println(username + " " + cif + " " + "-NoSuchElementException");
			}catch (NullPointerException e) {
				System.out.println(username + " " + cif + " " + "-NullPointerException");
			}catch(NoAlertPresentException e){
				System.out.println(username + " " + cif + " " + "-NoAlertPresentException");
			}catch (InvalidElementStateException e){
				System.out.println(username + " " + cif + " " + "-InvalidElementStateException");		
			}catch(UnhandledAlertException e){
				System.out.println(username + " " + cif + " " + "-UnhandledAlertException");	
			}
		
	}
	
	
	private void register(){
		ctrl.button.linkText("ลงทะเบียน");
		//ctrl.dropdown.robotByXpath("//*[@id='applicationForm']/div[3]/div/div/div[2]/div[2]/input", 3);
		ctrl.dropdown.name("workflowCode", "CSM");
		ctrl.button.xpath("//*[@id='applicationForm']/div[3]/div/div/div[26]/button[1]");
				
		System.out.println(username + " " + cif + " " + "Register");
	}
	
	private void customer(){
		ctrl.button.xpath("//*[@id='tabs-1']/div/div[1]/div[2]/button");
		ctrl.popup.byname("addClientFlag", 2);
		ctrl.radio.name("addClientFlag", 1);
		ctrl.radio.name("searchType", 9);
		ctrl.type.id("filterCIFNo", cif);
		ctrl.button.name("btnSearch");
		//ctrl.dropdown.robotByXpath("//*[@id='dropdownFixWidth']/input", 2);
		ctrl.dropdown.id("searchedCustType", "B");
		ctrl.radio.name("selectedCIF", 1);
		ctrl.button.xpath("//*[@id='table_search']/div[4]/button[1]");
		ctrl.button.xpath("//*[@id='dialog-modal']/table[3]/tbody/tr/th/button[1]");
		ctrl.popup.byxpath("//*[@id='tabs-1']/div/div[1]/div[2]/button", 1);
		
		System.out.println(username + " " + cif + " " + "Customer");
		
		ctrl.button.linkText("ข้อมูลอื่นๆ (แบบเต็ม)");
		ctrl.type.id("custPeriodYear", "10");
		ctrl.type.id("custPeriodMonth", "10");
		ctrl.dropdown.id("incomeSourceCode", "1");
		ctrl.dropdown.id("coborrowerRelatedTypeCode", "01");
		ctrl.dropdown.id("garantorRelatedTypeCode", "01");
		ctrl.type.id("childCountAll", "0");
		ctrl.type.id("childCountWork", "0");
		ctrl.type.id("curAddrYear", "10");
		ctrl.type.id("curAddrMonth", "0");
		ctrl.dropdown.id("houseTypeCode", "01");
		ctrl.dropdown.id("houseOwnershipCode", "01");
		ctrl.type.id("workCurExpYear", "5");
		ctrl.type.id("workCurExpMonth", "0");
		ctrl.type.id("workAllExpYear", "10");
		ctrl.type.id("workAllExpMonth", "0");
		ctrl.button.id("btnSaveDraft");

		System.out.println(username + " " + cif + " " + "Customer Full");

		ctrl.button.linkText("รายได้จากเงินเดือน");
		ctrl.type.id("workExpYear", "5");
		ctrl.type.id("workExpMonth", "10");		
		ctrl.type.id("regSalary", "30000");
		ctrl.type.id("regTax", "300");
		ctrl.type.id("regSocialSecurity", "750");
		ctrl.type.id("regProvidentFund", "100");
		ctrl.type.id("regCooperativeShare", "750");
		ctrl.type.id("regChorOrSor", "100");
		ctrl.jsExecute.forceExe("calRegularIncome();");
		ctrl.button.id("btnSaveDraft");
		
		System.out.println(username + " " + cif + " " + "Customer Salary");		

		ctrl.button.linkText("ค่าใช้จ่าย");
		ctrl.type.id("expenseConsumer", "10000");
		ctrl.jsExecute.forceExe("calNormalExpense();");
		ctrl.button.id("btnSaveDraft");
		
		System.out.println(username + " " + cif + " " + "Customer Expense");	
		
		ctrl.button.linkText("Non NCB");
		ctrl.type.id("unreportedCoopCompLoan", "10000");
		ctrl.button.id("btnSave");
		
		System.out.println(username + " " + cif + " " + "Customer Non NCB");	
	}
	
	private void loanApp(){
		ctrl.button.linkText("คำขอสินเชื่อ");
		alert();
		
		ctrl.button.xpath("//*[@id='tabs-1']/div[1]/div[1]/div/button");
		
		ctrl.popup.byxpath("//*[@id='normalDiv']/div[2]/input", 2);
		ctrl.dropdown.name("productGroupCode", "LN", "changeProductGroupCode(null,false);");
		ctrl.dropdown.name("productTypeCode", "7100", "changeProductTypeCode(null);");
		ctrl.dropdown.name("marketCodeCode", "1007", "changeMarketCodeCode(null);");
		ctrl.dropdown.name("campaignCode", "1", "changeCampaignCode(null);");
		ctrl.dropdown.name("campaignCode", "1", "changeCampaignCode(null);");
		ctrl.type.id("creditLimit", "1000000");
		ctrl.type.id("term", "100");
		ctrl.dropdown.name("collTypeId", "2", "changeCollTypeId(null);");
		ctrl.dropdown.name("ltvCode", "0-100", "changeLtvCode();");
		ctrl.button.xpath("//*[@id='normalDiv']/div[4]/button[1]");
		ctrl.popup.byxpath("//*[@id='tabs-1']/div[1]/div[1]/div/button", 1);

		ctrl.dropdown.name("purposeArrangementId", "24", "changePurposeArrangement(purposeArrangementId);");
		ctrl.dropdown.name("personalConsumptionId", "53");
		ctrl.dropdown.name("paymentMethodCode", "0");

		ctrl.button.id("btnSave");
		System.out.println(username + " " + cif + " " + "LoanApp");					
	}
	
	private void document(){
		ctrl.button.linkText("เอกสารที่ต้องการ");
		alert();
		ctrl.button.id("btnSave");
		System.out.println(username + " " + cif + " " + "RequireDocument");	
		
		new AttachFiles(ctrl).execute();
		System.out.println(username + " " + cif + " " + "AttachFiles");			
	}
	
	private void policy(){
		ctrl.button.linkText("เกณฑ์นโยบาย");
		
		alert();
	
		ctrl.waitFor.xpath("//*[@id='bankPolicyActionForm']/div[2]/div[10]/input");
		ctrl.dropdown.name("loanContraryToLawFlag", "1");
		ctrl.dropdown.name("collateralContraryToBankPolicyFlag", "1");	
		
		ctrl.dropdown.name("loanContraryToBankPolicyFlag", "1");
		ctrl.dropdown.name("loanContraryToStandardApprovalFlag", "1");
		ctrl.dropdown.name("kycFlag", "Y");
		
		ctrl.button.id("btnSave");
		System.out.println(username + " " + cif + " " + "Policy");		
	}
	
	private void sendwork(){
		ctrl.dropdown.id("responses", "Next", "showHideDivByEndReason('Next');");
		ctrl.button.id("btnSend");
		alert();
		
		System.out.println(username + " " + cif + " " + "SendWork");	
	}	
	
	private void verify(){
		boolean flag = ctrl.verifyData.urlContains("inboxAction.do");		
		System.out.println(username + " " + cif + " " + "Verrify " + flag);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void openBrowser(SystemBase system) {
		new OpenBrowser(ctrl, system).execute();
	}
	
	public void login(String username, SystemBase system) {
		new Login(ctrl, username, "testuser", system).execute();
	}
	
	public void logout() {
		new Logout(ctrl).execute();
		System.out.println(username + " " + cif + " " + "Logout");
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
