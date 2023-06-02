package main.world;

import java.util.EnumMap;
import java.util.LinkedList;

public class TrafficLight {
	
	private Direction place;
	
	private EnumMap<Direction, LightColor> actualColors;
	private EnumMap<Direction, LightColor> futureColors;

	private LinkedList<Vehicle> vehicles;
	
	public TrafficLight(Direction d) {
		place = d;
		
		actualColors = new EnumMap<>(Direction.class);
		futureColors = new EnumMap<>(Direction.class);

		for(Direction di : Direction.values()) {
			if(di != place) {
				actualColors.put(di, LightColor.RED);
				futureColors.put(di, LightColor.RED);
			}
		}
		
		vehicles = new LinkedList<>();
	}

	/**
	 * Simulates a step of the traffic light
	 * @return the vehicle that has been released, null if none
	 */
	public Vehicle step(boolean withColor) {
		if(withColor) {
			for (Direction dir : Direction.values()) {
				if(dir != place) {
					stepColorTransition(dir);
				}
			}
		}

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

	/**
	 * Executes colorchange for the given direction
	 * @param dir the direction of the lamp
	 */
	private void stepColorTransition(Direction dir){
		switch (actualColors.get(dir)) {
			case YELLOW -> actualColors.put(dir, LightColor.RED);
			case REDYELLOW -> actualColors.put(dir, LightColor.GREEN);
			default -> {}
		}
	}

	/**
	 * Changes the color of the traffic light, to the calculated optimal colors
	 */
	public void changeColor() {
		for(Direction dir : Direction.values()) {
			if(dir != place) {
				switch (actualColors.get(dir)) {
					case GREEN -> {
						if (futureColors.get(dir) == LightColor.RED)
							actualColors.put(dir, LightColor.YELLOW);
					}
					case RED -> {
						if (futureColors.get(dir) == LightColor.GREEN)
							actualColors.put(dir, LightColor.REDYELLOW);
					}
					default ->
						stepColorTransition(dir);
				}
			}
		}
	}

	/**
	 * Changes the future color of the lamp in the given direction
	 * @param dir the direction of the lamp
	 * @param c the future color, GREEN or RED
	 */
	public void changeFutureColor(Direction dir, LightColor c) {
		futureColors.put(dir, c);
	}

	public LightColor getColor(Direction dir) {
		return actualColors.get(dir);
	}

	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}

	public long getWaitingVehiclesWeight() {
		if(vehicles.isEmpty())
			return 0;

		long sum = 0;
		for(Vehicle v : vehicles) {
			sum += v.getWeight();
		}

		return sum;
	}

	public Direction getFirstDestination() {
		if(vehicles.isEmpty())
			return null;
		return vehicles.getFirst().getDestionation();
	}

	public int getWaitingVehiclesCount() {
		if(vehicles.isEmpty())
			return 0;
		return vehicles.size();
	}
}
