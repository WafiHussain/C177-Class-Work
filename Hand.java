// Libararies needed
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hand {
	public static void main(String[] args) throws ParseException {
		// Constructor
		public Hand();
		
		// Discarding all cards from the hand
		public void clear();
		
		// Adding the card c to the hand.
		public void addCard(Card c);
		
		// If the card is in the hand, removes it.
		public void removeCard(Card c);
		
		// Removes the card in the position 
		public void removeCard(int position);
		
		// Returning the number of cards back to the hand
		public int getCardCount();
		
		// Get the card from the hand
		public Card getCard(int position);
		
		// Sorting the cards in the hand
		public void sortBySuit();
		
		// Sort the cards by value
		public void sortByValue();
	}
}