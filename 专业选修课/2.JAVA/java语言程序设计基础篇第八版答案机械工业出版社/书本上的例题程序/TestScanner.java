import java.util.Scanner; // Scanner is in java.util

public class TestScanner {
  public static void main(String args[]) {
    // Create a Scanner
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter an integer
    System.out.print("Enter an integer: ");
    int intValue = input.nextInt();
    System.out.println("You entered the integer " + intValue);

    // Prompt the user to enter a double value
    System.out.print("Enter a double value: ");
    double doubleValue = input.nextDouble();
    System.out.println("You entered the double value "
      + doubleValue);

    // Prompt the user to enter a string
    System.out.print("Enter a string without space: ");
    String string = input.next();
    System.out.println("You entered the string " + string);
  }
}
