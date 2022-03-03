// More State Capitals (Capital.java)

public class Capital {
	
	// Properties
	private String name;
	private int population;
	private double sqMileage;
	
	// Making the properties public. 
	public Capital(String name, int population, double sqMileage) {
		this.name = name;
		this.population = population;
		this.sqMileage = sqMileage;
	}
	
	// Getters
	public String getName(){
		return name;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public double getSqMileage() {
		return sqMileage;
	}
	
	// Setters
	public String setName() {
		this.name = name;
	}
	
	public int setPopulation() {
		this.population = population;
	}
	
	public double setSqMileage() {
		this.sqMileage = sqMileage;
	}
	
}