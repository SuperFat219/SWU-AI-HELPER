// Exercise20_3.java: Find the GCD of two integers
import javax.swing.JOptionPane;

public class Exercise20_3JOptionPane {
  public static void main (String args[]) {
    // Enter the first number
    String s1 = JOptionPane.showInputDialog(
      "Enter the first number:");

    // Convert string to int
    int m = Integer.parseInt(s1);

    // Enter the second number
    String s2 = JOptionPane.showInputDialog(
      "Enter the second number:");

    // Convert string to int
    int n = Integer.parseInt(s2);

    System.out.println("The GCD of " + m + " and " + n + " is " +
      gcd(m, n));
  }

  public static int gcd(int m, int n) {
    if (m % n == 0)
      return n;
    else
      return gcd(n, m % n);
  }
}

