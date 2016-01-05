package testmodule;

import com.SystemBase;

import controller.Controller;
import creditapplication.AddCustomerList;
import creditapplication.AddLoanForm;
import creditapplication.CollateralAccount;
import creditapplication.CollateralLottery;
import creditapplication.Register;
import customcomponent.InterruptTask;
import newsm2.BaseApplication;
import newsm2.COMAutoElementConfig;
import testdata.CellTag.sheetType;
import variable.PathVariable;

public class TestModule extends BaseApplication {
	
	public TestModule(PathVariable pathVariable) {
		this.ctrl = new Controller(pathVariable);
		this.caeConfig = new COMAutoElementConfig(true);
	}

	public void run(){
		InterruptTask interrupt = new InterruptTask();
		
		caeConfig.BRO = "NiponM";
//		caeConfig.appID = "038312580133";
		
		openBrowser(SystemBase.LOR);
		login(caeConfig.BRO, SystemBase.LOR);
		new Register(ctrl).execute();		
		new AddCustomerList(ctrl, sheetType.AddNormalCustomer, "10850", true).execute();
		new AddLoanForm(ctrl, sheetType.LongTermLoan, 1).execute();
		interrupt.waitKey();

		new CollateralLottery(ctrl, 1).execute();
		new CollateralAccount(ctrl, 1).execute();		
		interrupt.waitKey();
		
		logout();
		ctrl.disconnect();
	}
	
}
