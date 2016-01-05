package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogToFile {

	private File file;
	private FileWriter fw;
	private BufferedWriter bw;
	private boolean writeAble;
	private String filePath;
	
	public LogToFile(String filePath) {
		this.filePath = filePath;
		startLog();
	}
	
	private void startLog(){
		try {
				System.out.printf("now: %s%n", LocalDateTime.now());			
				String currentDateTime = getLocalTime();
				String content = "Start log. " + currentDateTime;

				file = new File(filePath);

//				Force to create new file
				file.createNewFile();

				fw = new FileWriter(file.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				try {
					bw.write(content+"\r\n");
				} catch (IOException e) {
					System.out.println("Log writeln ... CRASH");
					e.printStackTrace();
				}	
				
				System.out.println("Log created ...");
			
				writeAble = true;
				

				bw.close();
				writeAble = false;

		} catch (IOException e) {
			System.out.println("Log created ... CRASH");
			e.printStackTrace();
		}
	}

	private void openFile(){
		try {
			file = new File(filePath);
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			writeAble = true;
		} catch (IOException e) {
			System.out.println("Log created ... CRASH");
			e.printStackTrace();
		}
	}
	
	private String getLocalTime() {
		String localTime = "-" + LocalDateTime.now().getYear() + "/"
				+ String.format("%02d", LocalDateTime.now().getMonthValue()) +"/"
				+ String.format("%02d", LocalDateTime.now().getDayOfMonth())
				+ "-"
				+ String.format("%02d", LocalDateTime.now().getHour()) + ":"
				+ String.format("%02d", LocalDateTime.now().getMinute()) + ":"
				+ String.format("%02d", LocalDateTime.now().getSecond());
		return localTime;
	}

	public void closeFile(){
		try{
			bw.close();
			writeAble = false;
			//System.out.println("Log end ...");
		} catch (IOException e) {
			System.out.println("Log end ... CRASH");
			e.printStackTrace();
		}
	}

	public void write(String logStr) {
		openFile();
		if(!writeAble)
			return;
		try {
			bw.write(logStr);
		} catch (IOException e) {
			System.out.println("Log write ... CRASH");
			e.printStackTrace();
		}		
		closeFile();
	}

	public void writeln(String logStr) {
		openFile();
		if(!writeAble)
			return;
		try {
			bw.write(logStr+"\r\n");
		} catch (IOException e) {
			System.out.println("Log writeln ... CRASH");
			e.printStackTrace();
		}	
		closeFile();
	}
}
