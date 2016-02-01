package csm;
import variable.PathVariable;

public class Runable implements Runnable {
	private Thread t;
	private String username;
	private String cif;

	public Runable(String username, String cif) {
		this.username = username;
		this.cif = cif;
		System.out.println("Creating " + username + " " + cif);
	}

	public void run() {
		System.out.println("Running " + username + " " + cif);

		for(int i=0; i<20; i++){
			System.out.println();
			System.out.println("Running " + username + " " + cif + " >>>>>>>>>>>>>>>>>>>>>>>>>> " + i);
			System.out.println();
			CSM csm = new CSM(getAssignedPath2(), username, cif);
			csm.run();
		}
		System.out.println("Thread " + username + " " + cif + " exiting.");
	}

	public void start() {
		System.out.println("Starting " + username + " " + cif);
		if (t == null) {
			t = new Thread(this, username);
			t.start();
		}
	}
	
	public PathVariable getAssignedPath2() {
		String LORbaseURL = "https://10.251.108.141:9443/LOR_3_18/";
		String CMSbaseURL = "http://172.31.1.42:9080/CMS/";
		String pathOffset = "C:\\testdata\\";
		String pathSpecify = "tc";
		
		PathVariable pathVariable = new PathVariable();
		pathVariable.setExcelName(pathOffset + pathSpecify + ".xls");
		pathVariable.setLogName(pathOffset + pathSpecify + ".log");
		pathVariable.setLORBaseURL(LORbaseURL);
		pathVariable.setCMSBaseURL(CMSbaseURL);
		
		return pathVariable;
	}
	
}