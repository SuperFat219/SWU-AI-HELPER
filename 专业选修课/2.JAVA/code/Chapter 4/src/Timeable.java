import java.util.Scanner;

/**
 * Time:2021.11.20
 * 4.22
 */
public class Timeable {
    public static void main(String[] args) {
        int numberOfYears;
        double loanAmount, rate, monthlyPayment, totalPayment, monthlyInterestRate, balance;

        Scanner input = new Scanner(System.in);
        System.out.print("Loan Amount: ");
        loanAmount = input.nextDouble();

        System.out.print("Number of Years: ");
        numberOfYears = input.nextInt();

        System.out.print("Annual Interest Rate: ");
        rate = input.nextDouble();

        monthlyPayment = loanAmount * (rate / 12)
                / (1 - 1 / Math.pow(1 + (rate / 12), numberOfYears * 12));
        totalPayment = monthlyPayment * numberOfYears * 12;
        balance = loanAmount;
        monthlyInterestRate = rate / 12;

        System.out.printf("Monthly Payment: %.2f\n", monthlyPayment);
        System.out.printf("Total Payment: %.2f\n\n", totalPayment);

        System.out.print("Payment#\tInterest\tPrincipal\tBalance\n");
        for (int i = 1; i <= numberOfYears * 12; i++) {
            double interest = monthlyInterestRate * balance;
            double principal = monthlyPayment - interest;
            balance = balance - principal;
            System.out.printf("%d\t\t%.2f\t\t%.2f\t\t%.2f\n", i, interest, principal, balance);
        }
    }
}
