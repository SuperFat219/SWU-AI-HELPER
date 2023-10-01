import java.util.Scanner;

public class Exercise2_10 {
  /** Main method */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Receive the amount entered from the keyboard
    System.out.print("Enter an amount in double, for example 11.56 ");
    double amount = input.nextDouble();

    int remainingAmount = (int)(amount * 100);

    // Find the number of one dollars
    int numberOfOneDollars = remainingAmount / 100;
    remainingAmount = remainingAmount % 100;

    // Find the number of quarters in the remaining amount
    int numberOfQuarters = remainingAmount / 25;
    remainingAmount = remainingAmount % 25;

    // Find the number of dimes in the remaining amount
    int numberOfDimes = remainingAmount / 10;
    remainingAmount = remainingAmount % 10;

    // Find the number of nickels in the remaining amount
    int numberOfNickels = remainingAmount / 5;
    remainingAmount = remainingAmount % 5;

    // Find the number of pennies in the remaining amount
    int numberOfPennies = remainingAmount;

    // Display results
    String output = "Your amount " + amount + " consists of \n" +
      numberOfOneDollars + " dollars\n" +
      numberOfQuarters + " quarters\n" +
      numberOfDimes + " dimes\n" +
      numberOfNickels + " nickels\n" +
      numberOfPennies + " pennies";
    System.out.println(output);
  }
}
