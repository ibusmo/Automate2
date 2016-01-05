package testdata;

import java.util.List;

import log.LogCat;
import log.LogTag.logaction;
import log.LogTag.logelement;
import log.LogTag.logexestatus;
import log.LogTag.logoperation;
import log.LogTag.logsubtab;
import log.LogTag.logtab;
import pools.ReadExcelController;
import pools.ReadExcelXLS;
import testdata.CellTag.col;

public class TestConfig {

	private LogCat logCat;
	private LoadElement regData;
	public List<DataAutoElementObj> autoElementObj;
	
	private logoperation logoperation;

	private String workBookPath;
	private String workSheetPath;
	private int sizeOfData;
	private int offsetRow;
	
	public TestConfig(String workBookPath, LogCat logCat){
		this.workBookPath = workBookPath;
		this.logCat = logCat;
		
		logoperation 	= log.LogTag.logoperation.Config;
		workSheetPath = "config_auto";

		sizeOfData = getSize();
		offsetRow = 2;
	}

	public List<DataAutoElementObj> getData(){
		loadData();
		return autoElementObj;
	}
	
	public boolean loadData(){
		regData = new LoadElement(workBookPath, workSheetPath, sizeOfData, offsetRow);
		if(regData.loadAutoData()){
			autoElementObj = regData.getConfigObject();
			sendToLogCustom(logexestatus.PASS, logaction.LoadData, null);
		}else{
			sendToLogCustom(logexestatus.FAIL, logaction.LoadData, null);		
			return false;	
		}
		return true;
	}
	
	private int getSize() {
		ReadExcelController rdExl = new ReadExcelXLS(workBookPath, workSheetPath);
		System.out.print(rdExl.getString(col.A, row(1)) + "\t");
		int size 		= (int) Math.round(Double.parseDouble(rdExl.getString(col.A, row(1))));
		System.out.println(size);
		rdExl.closeFile();
		return size;
	}
	
	private int row(int r){
		return r + offsetRow;
	}
	
	public void sendToLogCustom(logexestatus logexestatus, logaction logaction, String str) {
		logCat.sendToLog(logexestatus, logoperation, logtab.None, logsubtab.None, logelement.None,
				logaction, str);
	}
}
