package world;

public class Vehicle {
	private int weight;
	
	private Direction from;
	private Direction to;

	public Vehicle(Direction from, Direction to, int w) {
		this.from = from;
		this.to = to;
		
		weight = w;
	}
	
	

	public int getWeight(){
		return weight;
	}
}
