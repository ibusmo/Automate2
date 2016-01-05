package controller;

import customcomponent.WaitFor;
import webdriver.WebDriverEngine;

public class EngineController {

	public WebDriverEngine wde;
	public WaitFor waitFor;
	
	public EngineController(){
		wde 	= new WebDriverEngine();
		waitFor = new WaitFor(wde.getDriverWait());
	}
	
	public EngineController(String browser){
		wde 	= new WebDriverEngine(browser);
		waitFor = new WaitFor(wde.getDriverWait());
	}

}
