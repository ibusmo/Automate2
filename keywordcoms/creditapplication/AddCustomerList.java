package creditapplication;

import base.KeywordsCOM;
import controller.Controller;
import testdata.CellTag.sheetType;
import testdata.DataElementObj;

public class AddCustomerList extends KeywordsCOM {
	
	String cif;
	Boolean mainCustomerFlag;
	
	public AddCustomerList(Controller ctrl, sheetType customerType, String cif, Boolean mainCustomerFlag) {
		super.ctrl = ctrl;
		this.cif = cif;
		this.mainCustomerFlag = mainCustomerFlag;
		
		super.logoperation 		= log.LogTag.logoperation.RegScanning;
		super.logtab 			= log.LogTag.logtab.RegCustomer;
		
		switch(customerType){
			case AddNormalCustomer:
				super.logsubtab 		= log.LogTag.logsubtab.NormalCustomer;
				super.workSheetPath 	= "customer_add_1";
				break;
			case AddLegalCustomer:
				super.logsubtab 		= log.LogTag.logsubtab.LegalCustomer;
				super.workSheetPath 	= "customer_legal_add_1";
				break;
			default:
				break;
		}
	}

	@Override
	protected boolean preCondition(DataElementObj obj) {
		
		if(obj.fieldName.contains("filterCIFNo")){
			obj.data = cif;
		}
		if(mainCustomerFlag && obj.name.contains("ประเภทผู้ขอสินเชื่อ")){
			obj.data = "ผู้ขอสินเชื่อหลัก";
			obj.fieldValue = "2";
			obj.run = true;
		}else if(obj.name.contains("ประเภทผู้ขอสินเชื่อ")){
			obj.data = "ผู้ขอสินเชื่อร่วม";
			obj.fieldValue = "3";
			obj.run = true;
		}
		
		switch(workSheetPath){
			case "customer_add_1":
				if(obj.name.contains("เป็นการกู้โดยกลุ่ม")) {
					obj.run = false;
				}
				break;
				
			case "customer_legal_add_1":	
				if(mainCustomerFlag && obj.name.contains("เป็นการกู้โดยกลุ่ม")) {
					obj.data = "ไม่ใช่";
					obj.fieldValue = "2";
					obj.run = true;
				}
				else if(obj.name.contains("เป็นการกู้โดยกลุ่ม")){
					obj.run = false;					
				}
				break;
				
			default:
				break;
		}
		return true;
	}
		
}
