/*Number:2.1
 *Author:yzs
 *Time:2021.11.06 15:24
 */
import java.util.Scanner;
public class Calculate{
	public static void main(String[] args) {
		System.out.println("Enter a degree in Celsius");
		Scanner input = new Scanner(System.in);
		double celsius=input.nextDouble();//摄氏度
		double fahrenheit = celsius*9/5+32;
		System.out.println(celsius+" Celsius is "+fahrenheit+" Fahrenheit");
	}
}