import java.util.Scanner;

public class Exercise2_12b {
  public static void main(String args[]) {
    Scanner input = new Scanner(System.in);

    // Obtain input
    System.out.print("Enter balance: ");
    double balance = input.nextDouble();

    System.out.print("Enter annual interest rate: ");
    double annualInterestRate = input.nextDouble();
    double monthlyInterestRate = annualInterestRate / 1200;

    double interest = balance * monthlyInterestRate;

    // Display output
    System.out.println("The interest is " + (int)(100* interest) / 100.0);
  }
}
