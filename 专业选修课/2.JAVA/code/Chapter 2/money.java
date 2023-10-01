/*Number:2.5
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class money{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        System.out.println("Enter the subtotal and a gratuity rate:");
        double subtotal=input.nextDouble();
        double gratuity_rate=input.nextDouble();
        double gratuity=subtotal*gratuity_rate/100;
        System.out.printf("The gratuity is %.2f and total is %.2f",gratuity,(subtotal+gratuity));
	}
}