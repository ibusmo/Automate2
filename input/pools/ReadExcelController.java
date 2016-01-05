package pools;

import testdata.CellTag.col;
import testdata.CellTag.fieldType;
import testdata.CellTag.inputType;
import testdata.CellTag.sheetType;

public interface ReadExcelController {
	
	public sheetType getSheetType(col colTag, int rowTag);
	public boolean openFile();
	public boolean closeFile();
	public String getString(col colTag, int rowTag);
	public fieldType getFieldType(col colTag, int rowTag);
	public boolean getRun(col colTag, int rowTag);
	public inputType getIOType(col colTag, int rowTag);
	
}
