package testdata;

import java.util.ArrayList;
import java.util.List;

import pools.ReadExcelController;
import pools.ReadExcelXLS;
import testdata.CellTag.col;

public class LoadElement{

	String workBookPath;
	String workSheetPath;
	int sizeOfData;
	int offsetRow;
	List<DataElementObj> elmentObj;
	List<DataAutoElementObj> autoElementObj;
	ReadExcelController rdExl;
	
	public LoadElement(String workBookPath, String workSheetPath, int sizeOfData, int offsetRow){
		this.workBookPath = workBookPath;
		this.workSheetPath = workSheetPath;
		this.offsetRow = offsetRow;
		this.sizeOfData = sizeOfData;
	}

	private int row(int r){
		return r + offsetRow;
	}
	
	public boolean loadData() {
		rdExl = new ReadExcelXLS(workBookPath, workSheetPath);
		elmentObj = new ArrayList<DataElementObj>();
		for(int idx=1; idx<=sizeOfData; idx++){
			System.out.print(idx + " ");
			
			DataElementObj objTmp = new DataElementObj();
			System.out.print("B ");
			objTmp.name 		= rdExl.getString(col.B, row(idx));
			System.out.print("C ");
			objTmp.data 		= rdExl.getString(col.C, row(idx));
			
			System.out.print("D ");
			objTmp.type 		= rdExl.getIOType(col.D, row(idx));
			
			System.out.print("E ");
			objTmp.fieldValue 	= rdExl.getString(col.E, row(idx));
			System.out.print("F ");
			objTmp.fieldName 	= rdExl.getString(col.F, row(idx));
			
			System.out.print("G ");
			objTmp.fieldType 	= rdExl.getFieldType(col.G, row(idx));
			
			System.out.print("H ");
			objTmp.fieldOptional 	= rdExl.getString(col.H, row(idx));
			System.out.print("I ");
			objTmp.jsExe 		= rdExl.getString(col.I, row(idx));
			
			System.out.print("J ");
			objTmp.run 			= rdExl.getRun(col.J, row(idx));
			elmentObj.add(objTmp);

			System.out.print("B'" + objTmp.name + "'\t");
			System.out.print("C'" + objTmp.data + "'\t");
			System.out.print("D'" + objTmp.type + "'\t");
			System.out.print("E'" + objTmp.fieldValue + "'\t");
			System.out.print("F'" + objTmp.fieldName + "'\t");
			System.out.print("G'" + objTmp.fieldType + "'\t");
			System.out.print("H'" + objTmp.fieldOptional + "'\t");
			System.out.print("I'" + objTmp.jsExe + "'\t");
			System.out.print("J'" + objTmp.run + "'\t");
			System.out.println();
		}		
		rdExl.closeFile();
//			System.out.println("-------------------------------------");
//		for(elementObj objTmp : elmObj){
//			System.out.println(objTmp.toString());
//			System.out.println(objTmp.nameTag);
//			System.out.println(objTmp.dataTag);
//			System.out.println(objTmp.inputType);
//			System.out.println(objTmp.value);
//			System.out.println(objTmp.fieldName);
//			System.out.println(objTmp.filedType);
//			System.out.println(objTmp.fieldOpnal);
//			System.out.println(objTmp.jsExe);
//			System.out.println(objTmp.run);
//			System.out.println("-------------------------------------");
//		}	
		return true;
	}

	public List<DataElementObj> getObject() {
		return elmentObj;
	}

	public boolean loadAutoData() {
		rdExl = new ReadExcelXLS(workBookPath, workSheetPath);
		autoElementObj = new ArrayList<DataAutoElementObj>();
		for(int idx=1; idx<=sizeOfData; idx++){
			System.out.print(idx + " ");
			DataAutoElementObj objTmp = new DataAutoElementObj();			
			System.out.print("B ");
			objTmp.process 	= rdExl.getString(col.B, row(idx));
			System.out.print("C ");
			objTmp.sheet 	= rdExl.getSheetType(col.C, row(idx));
			System.out.print("D ");
			try{
				objTmp.index 	= (int)Double.parseDouble(rdExl.getString(col.D, row(idx)));
			}catch(NumberFormatException e){
				objTmp.index = 0;
			}														
			System.out.print("E ");
			objTmp.remark 	= rdExl.getString(col.E, row(idx));

			autoElementObj.add(objTmp);

			System.out.print("\t");
			System.out.print(objTmp.process + "\t");
			System.out.print(objTmp.sheet + "\t");
			System.out.print(objTmp.index + "\t");
			System.out.print(objTmp.remark + "\t");
			System.out.println();
		}		
		rdExl.closeFile();
		return true;
	}

	public List<DataAutoElementObj> getConfigObject() {
		return autoElementObj;
	}
}