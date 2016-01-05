package customcomponent;

import java.util.Scanner;

public class InterruptTask {
	
	@SuppressWarnings("resource")
	public boolean waitKey() {
		System.out.println("Enter to continue");
		new Scanner(System.in).nextLine();
		return true;
	}

	public boolean waitSleep(int times) {
		try {
			Thread.sleep(times);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;	
	}
	
}
