package main.world;

import java.util.ArrayList;

import main.junctionframework.JunctionFramework;

public class Simulator implements Runnable {
	
	ArrayList<Lamp> lamps = new ArrayList<>();
	
	private int time = 0;
	
	boolean stopped;
	
	private int speed = 1000;

	public Simulator() {
		stopped = false;
	}

	public void run() {

		while(!stopped) 
		{
		stepTime(true);
		
		try {
			Thread.sleep(speed);
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

	public void changeColor(Direction dir){

	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
