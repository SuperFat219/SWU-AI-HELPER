/*Number:2.11(1)
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class salary{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter employee's name:");
		String name=input.nextLine();
		System.out.println("Enter numbers of hours worked in a week:");
		double hours = input.nextDouble();
		System.out.println("Enter hourly pay rate:");
		double rate = input.nextDouble();
		System.out.println("Enter federal tax withholding rate:");
		double tax = input.nextDouble();
		System.out.println("Enter state tax withholding rate:");
		double tax2 = input.nextDouble();
		System.out.println("Employee's Name:"+name);
		System.out.println("hours worked:"+hours);
		System.out.println("Pay rate:  $"+rate);
		System.out.println("Gross Pay:  $"+hours*rate);
		System.out.println("Deductions:");
		double m1=hours*rate*tax;
		double m2=hours*rate*tax2;
		System.out.printf("\tFederal withholding(%.1f%%):  $%.1f\n",tax*100,m1);
		System.out.printf("\tStates withholding(%.1f%%)  $%.1f\n",tax2*100,m2);
		System.out.println("\tTotal Deduction:  $"+(m1+m2));
		System.out.println("Net pay:  $"+(hours*rate-m1-m2));
	}
}
	
