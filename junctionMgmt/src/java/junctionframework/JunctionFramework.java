package junctionframework;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import world.Simulator;

public class JunctionFramework {
	
	static MainFrame mainFrame = null;
	static Simulator simulator = null;
	
	public static void start() {
		simulator = new Simulator();
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
	public static void refresh() {
		if(mainFrame != null)
			mainFrame.refresh();
	}
	
	public static Simulator getSimulator() {
		return simulator;
	}
	
	public static void log(String message) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		System.out.println("["+timeStamp+"] " + message);
	}
}
