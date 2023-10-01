/*Number:2.7
 *Author:yzs
 *Time:2021.11.06
 */
import java.util.Scanner;
public class year{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of minuts:");
        long minutes=input.nextInt();
        long pre_data=minutes;
        long const_year=365*24*60;
        long const_day=24*60;
        long year=minutes/const_year;
        minutes=minutes-year*const_year;
        long day=minutes/const_day;
        System.out.println(minutes+" is approximately "+year+" and "+day+" days.");
    }
}