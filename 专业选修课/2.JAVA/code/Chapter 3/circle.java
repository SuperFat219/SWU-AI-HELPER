/*
 *Number:3.22
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Scanner;

public class circle {
    public static void main(String[] args) {
        System.out.println("Enter a point with two coordinates:");
        Scanner input = new Scanner(System.in);
        double x=input.nextDouble();
        double y=input.nextDouble();
        if (x*x+y*y<100)
        {
            System.out.println("Point("+x+", "+y+") is in the circle");
        }
        else
        {
            System.out.println("Point("+x+", "+y+") is not in the circle");
        }
    }
}
