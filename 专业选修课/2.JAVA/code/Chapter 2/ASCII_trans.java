/*Number:2.8
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class ASCII_trans{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an ASCII code:");
        int key=input.nextInt();
        System.out.printf("The Character for ASCII code %d is %c",key,key);
    }
}