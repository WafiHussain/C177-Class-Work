// Goblin Tower Exercise
import java.util.*;
import java.lang.*;

public class GoblinTower {

    // In order to Randomly start, we calculate the difference between
    // maximum and minimum and then add the minimum. Then return that value.
    public static int startRandomly(int minimum, int maximum){
        Random r = new Random();
        return r.nextInt(maximum - minimum) + minimum;
    }

    public static void main(String[] args){
        // Given the system objects
        Scanner inp = new Scanner(System.in);
        Random r = new Random();

        // Variables needed for the Hero
        int heroMaxHealth = 20; // Between 20 and 30
        int heroAttackPower = 3; // Between 1 and 3
        int heroDefencePower = 5; // Between 1 and 5
        char f = '2';
        char [] heroPotions = new char[startRandomly(1, 5)]; // There are 5 slots
        for(int i = 0; i < heroPotions.length; i++) {
            heroPotions[i] = f;
        }
        int heroGold = 0;

        // Variables needed for the Goblin
        int goblinHealth = 10; // Between 5 and 10
        int goblinAttackPower = 3; // Between 2 and 3
        int goblinDefencePower = 2; // Between 1 and 2

        // Fill up Hero variables
        heroMaxHealth = startRandomly(20, 30);
        System.out.println("Hero Health " + heroMaxHealth);
        heroAttackPower = startRandomly(1, 3);
        System.out.println("Hero Attack Power " + heroAttackPower);
        heroDefencePower = startRandomly(1, 5);
        System.out.println("Hero Defence Power: " + heroDefencePower);
        // The arrays
        System.out.println("Hero's Potions' size is " + heroPotions.length
                + " and looks like: " + Arrays.toString(heroPotions));
        System.out.println("Hero's Gold is " + heroGold + "\n");

        // Fill up Goblin variables
        goblinHealth = startRandomly(5, 10);
        System.out.println("Goblin's Health " + goblinHealth);
        goblinAttackPower = startRandomly(2, 3);
        System.out.println("Goblin's Attack Power: " + goblinAttackPower);
        goblinDefencePower = startRandomly(1, 2);
        System.out.println("Goblin's Defence Power: " + goblinDefencePower);
    }
}
