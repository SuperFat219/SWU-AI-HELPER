/*
 *Number:3.23
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Scanner;

public class rectangular {
    public static void main(String[] args) {
        System.out.println("Enter a point with two coordinates:");
        Scanner input = new Scanner(System.in);
        double x=input.nextDouble();
        double y=input.nextDouble();
        if (Math.abs(x)<5 && Math.abs(y)<2.5)
        {
            System.out.println("Point("+x+", "+y+") is in the rectangular");
        }
        else
        {
            System.out.println("Point("+x+", "+y+") is not in the rectangular");
        }
    }
}
