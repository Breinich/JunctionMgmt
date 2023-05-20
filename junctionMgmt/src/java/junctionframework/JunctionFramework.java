package junctionframework;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JunctionFramework {
	
	static MainFrame mainFrame = null;
	
	public static void start() {
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
	public static void refresh() {
		if(mainFrame != null)
			mainFrame.refresh();
	}
	
	public static void log(String message) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		System.out.println("["+timeStamp+"] " + message);
	}
}
