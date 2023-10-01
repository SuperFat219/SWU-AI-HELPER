/*Number:2.3 2.4
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class feet{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a value for feet:");
        double feet = input.nextDouble();
        System.out.println(feet+" feet is "+feet*0.305+" meters");
        System.out.println("Enter a number in pounds:");
        double pounds = input.nextDouble();
        System.out.println(pounds+" pounds is "+pounds*0.454+" kilograms");
    }
    
}