package world;

import java.util.ArrayList;

import junctionframework.JunctionFramework;

public class Simulator implements Runnable {
	
	ArrayList<Lamp> lamps = new ArrayList<Lamp>();
	
	private int time = 0;
	
	boolean stopped;
	
	public Simulator() {
		stopped = false;
	}

	public void run() {

		while(!stopped) 
		{
		stepTime(true);
		
		try {
			Thread.sleep(1000);
			} 
		catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		stopped = true;
	}

	private void stepTime(boolean b) {
		if(b) {
    		for(Lamp l : lamps)
    			l.step();
    		
    		time++;
    		
    	}

        JunctionFramework.refresh();
	}

}
