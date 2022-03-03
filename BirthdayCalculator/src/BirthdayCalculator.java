// Birthday Calculator
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.lang.String;

public class BirthdayCalculator {
    
    // Defining the birthDay as a String
    private static String birthDay;
    
    // The Main function that prints everything
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Birthday Calculator\n\n");
        
        System.out.println("Birthday?");
        birthDay = scan.nextLine();
        
        System.out.println("You were born in " + dayOfTheWeek(birthDay) + "!");
        System.out.println("This year will be " + dayOfTheWeek(dateOfTheYear(birthDay)) + "...");
        System.out.println("Today is " + getTodaysDate() + ",");
        System.out.println("We have only " + dayOfTheYear(birthDay) + " more days " + calculateAge(birthDay) + "!");
    }
    
    // The Day of the Week as a String
    public static String dayOfTheWeek(String day) {
        
        // Defining the birthDate as a String
        String birthDate = "";
        
        // Using the try function to define a block of code to be tested for errors while it is being executed.
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
            Date date1 = format1.parse(day);
            DateFormat format2 = new SimpleDateFormat("EEEE");
            birthDate = format2.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthDate;
    }
    
    // The Year Date as a String
    public static String dateOfTheYear(String date) {
        String dayOfTheYear = "";
        String lastDay = "";
        String newAge = "-2022";
        
        try {
            SimpleDateFormat format3 = new SimpleDateFormat("MM-dd-yyyy");
            Date date2 = format3.parse(date);
            DateFormat format4 = new SimpleDateFormat("MM-dd");
            lastDay = format4.format(date2);
            dayOfTheYear = lastDay + newAge;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayOfTheYear;
    }
    
    // Today's Date Getter as a String and returns the formatted String.
    public static String getTodaysDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
        String formatted = format1.format(c.getTime());
        return formatted;
    }
    
    
    // Day of the Year as an Integer. Returns num after storing the result
    public static int dayOfTheYear(String date) {
        int a[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        int num;
        
        num = a[month - 1] + day;
        
        if((year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && month > 2)
            num++;
            
        return num;
    }
    
    // Calculating the Age as an Integer. Calculates the round value and returns it.
    public static int calculateAge(String date){
        int a[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        int num = 0;
        int round = 2022;
        
        if ((month <= 03) && (year == round)) {
            round += 1;
        }
        return round - year;
    }
}
