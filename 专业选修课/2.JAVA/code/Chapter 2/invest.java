/*Number:2.13
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class invest{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter investment amount:");
        double amount=input.nextDouble();
        System.out.println("Enter annual interest rate:");
        double rate=input.nextDouble();
        System.out.println("Enter number of years:");
        double year=input.nextDouble();
        double value=Math.pow((1+rate/100),year)*amount;
        System.out.printf("Accumulated value is %.2f",value);
    }
}