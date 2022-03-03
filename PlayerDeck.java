// Player Deck
import java.util.Random;
import java.util.*;

// A class for Player Deck
public class PlayerDeck {
    
    // We implement a function that draws X numbers
    public static void cardDrawing(int card[]) {
        
        // Variables needed
        int drawing = 0;
        int control = 52;
        int discardingthePile[];
        
        Scanner inp = new Scanner(System.in);
        System.out.println("How many cards you like to draw?");
        drawing = inp.nextInt();
        
        // Print all the elements
        control += drawing;
        
        for(int i = 0; i < drawing; i++){
            System.out.println(card[i] + " ");
        }
        System.out.println("\n" + control);
    }
    
    // Shuffles and prints the array
    public static void shuffling(int card[], int num) {
        
        Random r = new Random();
        
        for(int i = 0; i < num; i++) {
            // Randomize the remaining positions
            int ra = i + r.nextInt(52 - i);
            
            // Swap the elements
            int tempo = card[ra];
            card[ra] = card[i];
            card[i] = tempo;
        }
    }
    
    // Main function
    public static void main(String[] args){
        // Array numbers between 0 to 51
        int arr[] = {0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22,
                23, 24, 25, 26, 27, 28, 29,
                30, 31, 32, 33, 34, 35, 36,
                37, 38, 39, 40, 41, 42, 43,
                44, 45, 46, 47, 48, 49, 50,
                51};
                
        shuffling(arr, 52);
        cardDrawing(arr);
        }
    }