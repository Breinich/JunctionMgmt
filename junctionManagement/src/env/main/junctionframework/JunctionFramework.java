package junctionframework;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import world.Simulator;

public class JunctionFramework {
	
	static MainFrame mainFrame = null;
	static Simulator simulator = null;
	
	public static void start() {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

		mainFrame = new MainFrame(simulator);
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
