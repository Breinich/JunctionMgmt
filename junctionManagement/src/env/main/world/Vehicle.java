package main.world;

public class Vehicle {
	private int weight;
	private int waitingTime;
	private final Direction from;
	private final Direction to;
	private final TrafficLight trafficLight;

	public Vehicle(Direction from, Direction to, int w, TrafficLight l) {
		this.from = from;
		this.to = to;
		
		weight = w;
		waitingTime = 0;

		trafficLight = l;
	}

	public Integer getWaitingTime() {
		return waitingTime;
	}

	public int getWeight(){
		return weight;
	}

	public Direction getDestionation() {
		return to;
	}

	/**
	 * Simulates one step of the vehicle
	 * increases the waiting time and the weight of the vehicle
	 */
	public void step() {
		waitingTime++;

		if(waitingTime > 15) {
			weight *= 2;
		}
		else
			weight++;
	}

	public Direction getFrom() {
		return from;
	}

	/**
	 * Checks if the vehicle can go
	 * @return true if the vehicle can go, false otherwise
	 */
	public boolean go() {
		return trafficLight.getColor(to) == Color.GREEN || trafficLight.getColor(to) == Color.YELLOW;
	}
}
