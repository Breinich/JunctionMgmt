package main.world;

import java.util.EnumMap;
import java.util.LinkedList;

public class TrafficLight {
	
	private Direction place;
	
	private EnumMap<Direction, Color> actualColors;
	private EnumMap<Direction, Color> futureColors;

	private LinkedList<Vehicle> vehicles;
	
	public TrafficLight(Direction d) {
		place = d;
		
		actualColors = new EnumMap<>(Direction.class);
		futureColors = new EnumMap<>(Direction.class);

		for(Direction di : Direction.values()) {
			if(di != place) {
				actualColors.put(di, Color.RED);
				futureColors.put(di, Color.RED);
			}
		}
		
		vehicles = new LinkedList<>();
	}
	
	public Vehicle step() {
		if(!vehicles.isEmpty()) {
			Vehicle ret = null;
			if(vehicles.getFirst().go()) {
				ret =  vehicles.removeFirst();
			}

			for (Vehicle v : vehicles) {
				v.step();
			}

			return ret;
		}

		return null;
	}
	
	public void changeColor() {
		//TODO change color
	}

	/**
	 * Changes the future color of the lamp in the given direction
	 * @param dir the direction of the lamp
	 * @param c the future color, GREEN or RED
	 */
	public void changeFutureColor(Direction dir, Color c) {
		futureColors.put(dir, c);
	}

	public Color getColor(Direction dir) {
		return actualColors.get(dir);
	}

	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}

	public int getWaitingVehiclesWeight() {
		int sum = 0;
		for(Vehicle v : vehicles) {
			sum += v.getWeight();
		}

		return sum;
	}
}
