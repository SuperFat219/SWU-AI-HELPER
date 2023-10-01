/*Number:2.9
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class compute_change{
 
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter an amount in int,for example 1156:");
		int amount=input.nextInt();;
		
		int dollars=amount/100;
		amount=amount%100;
		int quarters=amount/25;
		amount=amount%25;
		int dimes=amount/10;
		amount=amount%10;
		int nickels=amount/5;
		amount=amount%5;
		int pennys=amount;

		//显示
		System.out.println("Your amount " + amount + " consists of ");
	    System.out.println("\t"+dollars + " dollars");
	    System.out.println("\t"+quarters + " quarters");
	    System.out.println("\t"+dimes + " dimes");
	    System.out.println("\t"+nickels + " nickels");
	    System.out.println("\t"+pennys + " pennies");
	}
 
}