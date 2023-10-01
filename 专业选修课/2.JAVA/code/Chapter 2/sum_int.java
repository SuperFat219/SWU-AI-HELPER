/*Number:2.6
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class sum_int{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number between 0 and 1000:");
        int num=input.nextInt();
        int sum=0;
        while (num!=0)
        {
            sum+=num%10;
            num=num/10;
        }
        System.out.println("The sum of the digits is "+sum);
    }
}