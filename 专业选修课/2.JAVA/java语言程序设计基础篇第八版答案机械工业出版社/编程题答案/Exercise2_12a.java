import javax.swing.JOptionPane;

public class Exercise2_12a {
  public static void main(String args[]) {
    // Obtain input
    String balanceString = JOptionPane.showInputDialog(null,
      "Enter balance:");
    double balance = Double.parseDouble(balanceString);

    String interestRateString = JOptionPane.showInputDialog(null,
      "Enter annual interest rate:");
    double annualInterestRate = Double.parseDouble(interestRateString);
    double monthlyInterestRate = annualInterestRate / 1200;

    double interest = balance * monthlyInterestRate;

    // Display output
    JOptionPane.showMessageDialog(null, "The interest is " +
      (int)(100* interest) / 100.0);
  }
}
