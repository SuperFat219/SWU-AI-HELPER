/*Number:2.15
 *Author:yzs
 *Time:2021.11.07
 */
import java.util.Scanner;
public class sum_money{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the month:");
		int month = input.nextInt();
		double sum=0;
		double rate=0.05/12;
		for (int i=1;i<=month;i++)
		{
			sum+=100;
			sum=sum*(1+rate);
		}
		System.out.printf("After %d month,the sum of money is %.3f",month,sum);
	}
}