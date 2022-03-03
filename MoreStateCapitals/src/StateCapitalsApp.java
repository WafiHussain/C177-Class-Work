// More State Capitals
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class StateCapitalsApp {
    
    // Hash Map
    private final Map<String, Capital> stateCapitals = new HashMap<>();
	
	// The main function
	public static void main(String[] args) throws IOException {
		StateCapitalsApp stateCapitals = new StateCapitalsApp();
		stateCapitals.createAMap();
		stateCapitals.sizeOfTheMap();
		stateCapitals.keyValPairs();
		stateCapitals.limitPopulation();
		stateCapitals.limitSqMileage();
	}
	
	// The 5 functions based on what's added in the main function
	// Creating a Map
	public void createAMap() throws IOException {
		
		// This is where we read in the file
		System.out.println("Importing file data");
		Scanner inpFile = new Scanner(new File("MoreStateCapitals.txt")).useDelimiter("::|\\r\\n");
		
		while(inpFile.hasNext()) {
			String nameOfState = inpFile.next();
			String nameOfCapital = inpFile.next();
			int population = inpFile.nextInt();
			double sqMileage = inpFile.nextDouble();
			
			stateCapitals.put(nameOfState, new Capital(nameOfCapital, population, sqMileage));
		}
		inpFile.close();
	}
	
	// Printing the Size of the Map
	public void sizeOfTheMap() {
		System.out.println("The size of the map is: " + stateCapitals.size());
	}
	
	// Printing the Key Value Pairs
	public void keyValPairs() {
		System.out.println("\nPrinting the key-value pairs");
		Set<String> keys = stateCapitals.keySet();
		for(String key : keys)
			System.out.printf("%s, %s, %d, %.2\n", key, stateCapitals.get(key).getName(), 
							stateCapitals.get(key).getPopulation(),
							stateCapitals.get(key).getSqMileage());
	}
	
	// A function to limit the population 
	public void limitPopulation() {
		System.out.println("\nEnter the lower limit for capital city population: ");
		Scanner input = new Scanner(System.in);
		int minimumPop = input.nextInt();
		
		System.out.println("\nPrinting the key-value pairs");
		Set<String> keys = stateCapitals.keySet();
		for(String key : keys)
			System.out.printf("%s, %s, %d, %.2\n", key, stateCapitals.get(key).getName(), 
							stateCapitals.get(key).getPopulation(),
							stateCapitals.get(key).getSqMileage());
	}
	
	// Limit the Square Mileage
	private void limitSqMileage() {
		System.out.println("\nEnter the upper limit for capital city square mileage: ");
		Scanner input = new Scanner(System.in);
		int minimumPop = input.nextInt();
		
		System.out.println("\nPrinting the key-value pairs");
		Set<String> keys = stateCapitals.keySet();
		for(String key : keys)
			System.out.printf("%s, %s, %d, %.2\n", key, stateCapitals.get(key).getName(), 
							stateCapitals.get(key).getPopulation(),
							stateCapitals.get(key).getSqMileage());
	}
}
