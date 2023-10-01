/*Number:2.2
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class volume{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the radius and length of a cylinder:");
        double radius = input.nextDouble();
        double length = input.nextDouble();
        double area = radius * radius *Math.PI;
        double volume = area*length;
        System.out.printf("The area is %.4f\n",area);
        System.out.printf("The volume is %.1f",volume);
    }
}