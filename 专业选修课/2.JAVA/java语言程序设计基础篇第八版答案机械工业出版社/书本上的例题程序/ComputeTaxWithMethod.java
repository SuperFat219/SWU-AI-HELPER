import java.util.Scanner; 

public class ComputeTaxWithMethod {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter filing status
    System.out.print("Enter the filing status: ");
    int status = input.nextInt();

    // Prompt the user to enter taxable income
    System.out.print("Enter the taxable income: ");
    double income = input.nextDouble();

    // Display the result
    System.out.println("Tax is " +
      (int)(computeTax(status, income) * 100) / 100.0);
  }

  public static double computeTax(double income,
      int r1, int r2, int r3, int r4, int r5) {
    double tax = 0;

    if (income <= r1)
      tax = income * 0.10;
    else if (income <= r2)
      tax = r1 * 0.10 + (income - r1) * 0.15;
    else if (income <= r3)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (income - r2) * 0.27;
    else if (income <= r4)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 +
        (r3 - r2) * 0.27 + (income - r3) * 0.30;
    else if (income <= r5)
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.27 +
        (r4 - r3) * 0.30 + (income - r4) * 0.35;
    else
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.27 +
        (r4 - r3) * 0.30 + (r5 - r4) * 0.35 + (income - r5) * 0.386;

    return tax;
  }

  public static double computeTax(int status, double income) {
    switch (status) {
      case 0: return
        computeTax(income, 6000, 27950, 67700, 141250, 307050);
      case 1: return
        computeTax(income, 12000, 46700, 112850, 171950, 307050);
      case 2: return
        computeTax(income, 6000, 23350, 56425, 85975, 153525);
      case 3: return
        computeTax(income, 10000, 37450, 96700, 156600, 307050);
      default: return 0;
    }
  }
}
