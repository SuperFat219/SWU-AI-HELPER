import java.util.*;

public class Exercise9_16RegularExpression {
  /**Main method*/
  public static void main(String[] args) {
    // Prompt the user to enter the amount as a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter an amount as a string: ");
    String amountString = input.nextLine();

    String[] elements = amountString.split("[.|\n|\r|\t]");
    int dollars = 0;
    if (elements[0].length() > 0) {
      dollars = Integer.parseInt(elements[0]);
    }

    int remainingAmount = 0;
    int cents = 0;

    if (elements.length > 1) {
      remainingAmount = cents = Integer.parseInt(elements[1]);
    }

    remainingAmount = remainingAmount % 100;

    // Find the number of quarters in the remaining amount
    int numOfQuarters = remainingAmount / 25;
    remainingAmount = remainingAmount % 25;

    // Find the number of dimes in the remaining amount
    int numOfDimes = remainingAmount / 10;
    remainingAmount = remainingAmount % 10;

    // Find the number of nickels in the remaining amount
    int numOfNickels = remainingAmount / 5;
    remainingAmount = remainingAmount % 5;

    // Find the number of pennies in the remaining amount
    int numOfPennies = remainingAmount;

    // Display results
    if (remainingAmount < 0) {
       System.out.println("Your amount is negative");
       System.exit(0);
    }
    else if (remainingAmount < 0) {
       System.out.println("Your amount is zero");
       System.exit(0);
    }

    System.out.println("Your amount " + remainingAmount + " consists of ");

    if (dollars > 1)
      System.out.println(dollars + "\t dollars");
    else if (dollars == 1)
      System.out.println(dollars + "\t dollar");

    if (numOfQuarters > 1)
      System.out.println(numOfQuarters + "\t quarters");
    else if (numOfQuarters == 1)
      System.out.println(numOfQuarters + "\t quarter");

    if (numOfDimes > 1)
      System.out.println(numOfDimes + "\t dimes");
    else if (numOfDimes == 1)
      System.out.println(numOfDimes + "\t dime");

    if (numOfNickels > 1)
      System.out.println(numOfNickels + "\t nickels");
    else if (numOfNickels == 1)
      System.out.println(numOfNickels + "\t nickel");

    if (numOfPennies > 1)
      System.out.println(numOfPennies + "\t pennies");
    else if (numOfPennies == 1)
      System.out.println(numOfPennies + "\t pennie");
  }
}
