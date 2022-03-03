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
	
	public double getSqMileage1() {
		return sqMileage;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPopulationint(int population) {
		this.population = population;
	}
	
	public double getSqMileage() {
		return sqMileage;
	}
	
	public void setSqMileage(double sqMileage) {
		this.sqMileage = sqMileage;
	}
	
}
