/*Number:2.25
 *Author:yzs
 *Time:2021.11.07
 */
import java.util.Scanner;
public class time{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		long totalMillionSeconds=System.currentTimeMillis();
		long totalSeconds = totalMillionSeconds/1000;
		long currentSeconds = totalSeconds % 60;
		long totalMinutes = totalSeconds /60;
		long currentMinutes = totalMinutes %60;
		long totalHours = currentMinutes/60;
		//System.out.println(totalHours);
		long currentHours=totalHours%24;
		//System.out.println(currentHours);
		System.out.println("Enter the time zone offset to GMT:");
		int bias = input.nextInt();
		currentHours=(currentHours+24-bias)%24;
		System.out.printf("The current time is %d:%d:%d",currentHours,currentMinutes,currentSeconds);
	}
}