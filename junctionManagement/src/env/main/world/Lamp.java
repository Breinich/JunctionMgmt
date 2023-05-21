package world;

import java.util.ArrayList;
import java.util.HashMap;

public class Lamp {
	
	private Direction place;
	
	private HashMap<Direction, Color> colors;
	
	private ArrayList<Vehicle> vehicles;
	
	public Lamp(Direction d) {
		place = d;
		
		colors = new HashMap<Direction, Color>();
		for(Direction di : Direction.values()) {
			if(di != place) {
				colors.put(di, Color.RED);
			}
		}
		
		vehicles = new ArrayList<Vehicle>();
	}
	
	public void step() {
		colors.forEach((Direction dir, Color color)->{
			switch(color) {
			case YELLOW:
				colors.put(dir, Color.RED);
				break;
			case REDYELLOW:
				colors.put(dir, Color.GREEN);
				break;
			default:
				break;
			}
		});
	}
	
	public void changeColor(Direction dir) {
		Color currentColor = colors.get(dir);
		
		switch(currentColor) {
			case RED:
				colors.put(dir, Color.REDYELLOW);
				break;
			case GREEN:
				colors.put(dir, Color.YELLOW);
				break;
			default:
				break;
		}
	}
	
	public int getWaitingVehiclesWeight() {
		int sum = 0;
		for(Vehicle v : vehicles) {
			sum += v.getWeight();
		}
		
		return sum;
	}
}
