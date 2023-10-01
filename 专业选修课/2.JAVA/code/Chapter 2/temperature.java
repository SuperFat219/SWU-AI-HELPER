/*Number:2.17
 *Author:yzs
 *Time:2021.11.07
 */
import java.util.Scanner;
public class temperature{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the temperature in Fahrenheit:");
		double fahrenheit = input.nextDouble();
		System.out.println("Enter the wind speed miles per hour:");
		double wind_speed = input.nextDouble();
		double wind_chill_index = 35.74+0.6215*fahrenheit-35.75*Math.pow(wind_speed,0.16)+0.4275*fahrenheit*Math.pow(wind_speed,0.16);
		System.out.printf("The wind chill index is %.5f",wind_chill_index);
	}
}