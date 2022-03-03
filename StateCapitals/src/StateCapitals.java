// State Capitals
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;


public class StateCapitals {
	
	// The main function
	public static void main(String[] args) {
	    
	    // Adding a HashMap with each state/capital pair
		HashMap<String, String> stateCapitals = new HashMap<>();
		
		Set<String> keys = stateCapitals.keySet();
		
		stateCapitals.put("Alabama", "Montgomery");
		stateCapitals.put("Alaska", "Juneau");
		stateCapitals.put("Arizona", "Pheonix");
		stateCapitals.put("Arkansas", "Little Rock");
		
		
		System.out.println("STATES:\n======= ");
		for(String currentKey: keys){
		    String currentState = stateCapitals.get(currentKey);
		    System.out.println(currentKey);
		}
		
		System.out.println("CAPITALS:\n======= ");
		for(String currentKey : keys) {
		    String currentState = stateCapitals.get(currentKey);
		    System.out.println(currentState);
		}
		
		System.out.println("STATES/CAPITALS:\n======= ");
		for(String currentKey : keys) {
		    String currentState = stateCapitals.get(currentKey);
		    System.out.println(currentKey + "," + currentState);
		}
		
	}
	
}
