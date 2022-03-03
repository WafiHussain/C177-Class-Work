// Player Deck
// Libararies needed
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Card Class
public class Card {
 public static void main(String[] args) throws ParseException {
    public final static int SPADES = 0;
    public final static int HEARTS = 1;
    public final static int DIAMONDS = 2;
    public final static int CLUBS = 3;
    public final static int JOKER = 4;
    
    public final static int ACE = 1; // Non-numeric cards
    public final static int JACK = 11; 
    public final static int QUEEN = 12;
    public final static int KING = 13;
    
    // The suit of the card. One of the constants are SPADES, HEARTS, DIAMONDS, CLUBS or JOKER.
    private final int suit;
    
    // The value of the card
    private final int value;
    
    // This will create a Joker, with 1 as the associated value.
    public Card() {
        suit = JOKER;
        value = 1;
    }
	
	// We create a card with a specified suit and value
	public Card(int theValue, int theSuit) {
		if(theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS && theSuit != CLUBS && theSuit != JOKER)
			throw new IllegalArgumentException("Illegal playing card suit");
		if(theSuit != JOKER && (theValue < 1 || theValue > 13))
			throw new IllegalArgumentException("Illegal playing card value");
		value = theValue;
		suit = theSuit;
	}
	
	// Returning the suit card as a getter
	public int getSuit(){
	    return suit;
	}
	
	// Return a value card as a getter
	public int getValue() {
	 return value;   
	}
	
	// Returning a String representation of the card's suit
	public String getSuitAsString() {
	    switch(suit){
	        case SPADES: return "Spades";
	        case HEARTS: return "Hearts";
	        case DIAMONDS: return "Diamonds";
	        case CLUBS: return "Clubs";
	        default: return "Joker";
	    }   
	}
	
	// Returning a String representation of the card's vision
	public String getValueAsString() {
	    if(suit == JOKER)
	        return "" + value;
	    else{
	        switch(value){
	            case 1: return "Ace";
	            case 2: return "2";
	            case 3: return "3";
	            case 4: return "4";
	            case 5: return "5";
	            case 6: return "6";
	            case 7: return "7";
	            case 8: return "8";
	            case 9: return "9";
	            case 10: return "10";
	            case 11: return "11";
	            case 12: return "12";
	            default: return "King";
	        }
	    }
	}
	
	// Returning a String representation of the card
	public String toString() {
	    if(suit == JOKER) {
	        if(value == 1)
	            return "Joker";
	       else
	            return "Joker &" + value;
	    }
	    else
	        return getValueAsString() + "of" + getSuitAsString();
	}
	}
	}
	
    
    }
    /*public static void printHello() {
        System.out.println("");
    }*/
}