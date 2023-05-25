package main.junctionframework;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import main.Env;
import main.world.Simulator;

public class JunctionFramework {
	
	private static MainFrame mainFrame = null;
	private static Simulator simulator = null;

	private static Env environment = null;

	private JunctionFramework() {}
	
	public static void start(Object syncObject) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
				 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

		simulator = new Simulator(syncObject);

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

	public static void setEnvironment(Env env) {
		environment = env;
	}

	public static Env getEnvironment(){
		return environment;
	}
	
	public static void log(String message) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
		System.out.println("["+timeStamp+"] " + message);
	}
}
