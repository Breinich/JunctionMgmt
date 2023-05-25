package main.world;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import main.junctionframework.JunctionFramework;
import main.junctionframework.TimeStepper;

public class Simulator {
	
	EnumMap<Direction, TrafficLight> lamps;
	EnumMap<Direction, Integer> carSumByDirection;
	EnumMap<Direction, Integer> carAvgWaitingByDirection;
	ArrayList<Vehicle> actualMovingVehicles;
	Random random;
	
	private int time = 0;
	boolean stopped;
	private int greenDuration = 10;
	private final Object syncObject;
	private TimeStepper timeStepper = null;

	/**
	 * Creates a new simulator
	 * @param syncObject the object to synchronize the traffic lights' color changing with
	 */
	public Simulator(Object syncObject) {
		stopped = false;
		this.syncObject = syncObject;
		random = new Random();

		lamps = new EnumMap<>(Direction.class);
		carSumByDirection = new EnumMap<>(Direction.class);
		carAvgWaitingByDirection = new EnumMap<>(Direction.class);
	}

	/**
	 * Simulate one step of the simulation
	 */
	public void stepTime() {
		actualMovingVehicles = new ArrayList<>();
		time++;
		if(time % greenDuration == 0) {
			synchronized (syncObject) {
				for(TrafficLight l : lamps.values())
					l.changeColor();
			}
		}

		for(TrafficLight l : lamps.values()){
			Vehicle v = l.step();
			if(v != null)
				vehicleLeaves(v);
		}

        JunctionFramework.refresh();
	}

	/**
	 * A new vehicle is arriving to the junction
	 * @param direction the direction the vehicle is coming from
	 */
	public void addVehicle(Direction direction) {
		TrafficLight l = lamps.get(direction);
		Direction destination = null;
		while(destination == null || destination == direction)
			destination = Direction.values()[random.nextInt(Direction.values().length+1)-1];

		Vehicle v = new Vehicle(direction, destination, 1, l);

		l.addVehicle(v);

		JunctionFramework.log("Vehicle added to " + direction + " direction");

		JunctionFramework.getEnvironment().notifyAgentNewVehicle(direction);
	}

	/**
	 * A vehicle is leaving the junction
	 * @param vehicle the vehicle that is leaving
	 */
	public void vehicleLeaves(Vehicle vehicle){
		actualMovingVehicles.add(vehicle);

		Direction direction = vehicle.getFrom();
		int waitingTime = vehicle.getWaitingTime();
		int weight = vehicle.getWeight();

		carSumByDirection.put(direction, carSumByDirection.get(direction) + 1);
		carAvgWaitingByDirection.put(direction,
				(carAvgWaitingByDirection.get(direction)*(carSumByDirection.get(direction)-1) + waitingTime)/
						carSumByDirection.get(direction));

		JunctionFramework.log("Vehicle left from " + direction + " direction");

		JunctionFramework.getEnvironment().notifyAgentVehicleLeft(direction, weight);
	}

	public void setGreenDuration(int value) {
		greenDuration = value;
	}

	public void setSpeed(int speed) {
		timeStepper.setSpeed(speed);
	}

	public void stop() {
		timeStepper.stop();
	}

	/**
	 * Starts the simulation
	 */
	public void start() {
		if(timeStepper != null)
			timeStepper.stop();

		timeStepper = new TimeStepper(this);

		new Thread(timeStepper).start();
	}

	public Integer getAvgWaitingByDirection(Direction d) {
		return carAvgWaitingByDirection.get(d);
	}

	public Integer getCarSumByDirection(Direction d) {
		return carSumByDirection.get(d);
	}

	public Integer getTime() {
		return time;
	}

	public List<Vehicle> getActualMovingVehicles() {
		return actualMovingVehicles;
	}
}
