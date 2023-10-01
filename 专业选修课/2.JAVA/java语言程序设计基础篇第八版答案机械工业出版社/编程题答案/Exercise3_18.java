import java.util.Scanner;

public class Exercise3_18 {
  /** Main method */
  public static void main(String args[]) {
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter a year
    System.out.print("Enter a year: ");

    // Convert the string into an int value
    int year = input.nextInt();

    // Check if the year is a leap year
    boolean isLeapYear =
      ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);

    // Display the result in a message dialog box
    System.out.println(year + " is a leap year? " + isLeapYear);
  }
}
