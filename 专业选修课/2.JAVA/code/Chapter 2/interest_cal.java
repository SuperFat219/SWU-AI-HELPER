/*Number:2.12(1)
 *Author:yzs
 *Time:2021.11.07
*/
import java.util.Scanner;
public class interest_cal{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter balance and interest rate(e.g.,3 for 3%):");
        double balance=input.nextDouble();
        double rate=input.nextDouble();
        double interest = balance*rate/1200;
        System.out.printf("The interest is %.5f",interest);
    }
}