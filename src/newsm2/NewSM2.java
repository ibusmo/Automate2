package newsm2;
import customcomponent.InterruptTask;
import controller.Controller;
import variable.PathVariable;

public class NewSM2 {

	private Controller ctrl;

	private COMAutoElementConfig caeConfig;

	public NewSM2(PathVariable pathVariable) {
		ctrl = new Controller(pathVariable);
		caeConfig = new COMAutoElementConfig(true);
	}

	public void run() {
		InterruptTask interrupt = new InterruptTask();
		caeConfig.loadConfigAndSetting(ctrl.testConfig.getData());
		caeConfig.showSetting();
		
		new CreditApplication(ctrl, caeConfig).run();		
//		interrupt.waitKey();

		new CMSValuations(ctrl, caeConfig).run();			
//		interrupt.waitKey();
		
		new CreditAnalysis(ctrl, caeConfig).run();			
//		interrupt.waitKey();
		
		new CreditApproval(ctrl, caeConfig).run();			
//		interrupt.waitKey();

		new Contract(ctrl, caeConfig).run();				
		interrupt.waitKey();
		
//		new LimitSet(ctrl, caeConfig).run();				
//		interrupt.waitKey();
		
		ctrl.disconnect();
	}
	
}