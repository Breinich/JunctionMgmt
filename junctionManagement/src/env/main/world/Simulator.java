package main.world;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;

import main.junctionframework.JunctionFramework;
import main.junctionframework.TimeStepper;

public class Simulator {
	
	EnumMap<Direction, TrafficLight> trafficLights;
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

		trafficLights = new EnumMap<>(Direction.class);
		for(Direction d : Direction.values())
			trafficLights.put(d, new TrafficLight(d));

		carSumByDirection = new EnumMap<>(Direction.class);
		for (Direction d : Direction.values())
			carSumByDirection.put(d, 0);

		carAvgWaitingByDirection = new EnumMap<>(Direction.class);
		for (Direction d : Direction.values())
			carAvgWaitingByDirection.put(d, 0);

		actualMovingVehicles = new ArrayList<>();

		JunctionFramework.refresh();
	}

	/**
	 * Simulate one step of the simulation
	 */
	public void stepTime() {
		actualMovingVehicles.clear();
		time++;
		if(time % greenDuration == 0) {
			synchronized (syncObject) {
				for(TrafficLight l : trafficLights.values())
					l.changeColor();
			}

			for(TrafficLight l : trafficLights.values()){
				Vehicle v = l.step(false);
				if(v != null)
					vehicleLeaves(v);
			}
		}
		else{
			for(TrafficLight l : trafficLights.values()){
				Vehicle v = l.step(true);
				if(v != null)
					vehicleLeaves(v);
			}
		}

        JunctionFramework.refresh();
		for(Direction d : Direction.values())
			JunctionFramework.getEnvironment().notifyAgentNewBid(d, trafficLights.get(d).getWaitingVehiclesWeight(), trafficLights.get(d).getWaitingVehiclesCount());
	}

	/**
	 * A new vehicle is arriving to the junction
	 * @param direction the direction the vehicle is coming from
	 */
	public void addVehicle(Direction direction) {
		TrafficLight l = trafficLights.get(direction);
		Direction destination = null;
		while(destination == null || destination == direction)
			destination = Direction.values()[random.nextInt(Direction.values().length)];

		Vehicle v = new Vehicle(direction, destination, 1, l);

		l.addVehicle(v);

		JunctionFramework.log("Vehicle added to " + direction + " direction");

		JunctionFramework.getEnvironment().notifyAgentNewBid(direction, l.getWaitingVehiclesWeight(), l.getWaitingVehiclesCount());
	}

	/**
	 * A vehicle is leaving the junction
	 * @param vehicle the vehicle that is leaving
	 */
	public void vehicleLeaves(Vehicle vehicle){
		actualMovingVehicles.add(vehicle);

		Direction direction = vehicle.getFrom();
		int waitingTime = vehicle.getWaitingTime();

		carSumByDirection.put(direction, carSumByDirection.get(direction) + 1);
		carAvgWaitingByDirection.put(direction,
				(carAvgWaitingByDirection.get(direction)*(carSumByDirection.get(direction)-1) + waitingTime)/
						carSumByDirection.get(direction));

		JunctionFramework.log("Vehicle left from " + direction + " direction");
	}

	public void setGreenDuration(int value) {
		greenDuration = value;
	}

	public void setSpeed(int speed) {
		if(timeStepper != null)
			timeStepper.setSpeed(speed);
	}

	public void stop() {
		if(timeStepper != null)
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

	public Direction getDestination(Direction direction) {
		return trafficLights.get(direction).getFirstDestination();
	}

	public TrafficLight getTrafficLight(Direction dir) {
		return trafficLights.get(dir);
	}
}
