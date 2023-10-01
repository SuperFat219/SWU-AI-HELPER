/*Number:2.14
 *Author:yzs
 *Time:2021.11.07
 */
import java.util.Scanner;
public class BMI{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        System.out.println("Enter weights in pounds");
        double weight=input.nextDouble();
        System.out.println("Enter height in inched:");
        double height=input.nextDouble();
        double BMI=weight*0.45359237/(Math.pow(height*0.0254,2));
        System.out.printf("BMI is %.4f",BMI);
	}
}