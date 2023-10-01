import javax.swing.JOptionPane;

public class Exercise4_22 {
  public static void main(String[] args) {
    int numOfYears;
    double loanAmount;

    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter loan amount
    System.out.print(
      "Enter loan amount, for example 120000.95: ");
    loanAmount = input.nextDouble();

    // Enter number of years
    System.out.print(
      "Enter number of years as an integer, \nfor example 5: ");
    numOfYears = input.nextInt();

    // Enter yearly interest rate
    System.out.print(
      "Enter yearly interest rate, for example 8.25: ");

    // Convert string to double
    double annualInterestRate =
      input.nextDouble();

    // Obtain monthly interest rate
    double monthlyInterestRate = annualInterestRate/1200;

    // Compute mortgage
    double monthlyPayment = loanAmount*monthlyInterestRate /
      (1 - (Math.pow(1 / (1 + monthlyInterestRate), numOfYears * 12)));

    double balance = loanAmount;
    double interest;
    double principal;

    System.out.println("Loan Amount: " + loanAmount);
    System.out.println("Number of Years: " + numOfYears);
    System.out.println("Interest Rate: " + annualInterestRate + "%");
    System.out.println();
    System.out.println("Monthly Payment: " + (int)(monthlyPayment * 100) / 100.0 );
    System.out.println("Total Payment: " + (int)(monthlyPayment * 12 * numOfYears * 100) / 100.0 + "\n" );

    // Display the header
    System.out.println("Payment#\tInterest\tPrincipal\tBalance");
    int i;
    for (i = 1; i <= numOfYears * 12; i++) {
      interest = (int)(monthlyInterestRate * balance * 100) / 100.0;
      principal = (int)((monthlyPayment - interest) * 100) / 100.0;
      balance = (int)((balance - principal) * 100) / 100.0;
      System.out.println(i + "\t\t" + interest
        + "\t\t" + principal + "\t\t" + balance);
    }
  }
}
