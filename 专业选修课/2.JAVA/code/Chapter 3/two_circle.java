/*
 *Number:3.29
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Scanner;

public class two_circle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter circle1's center x-, y-coordinates and radius:");
        double r1_x=input.nextDouble();
        double r1_y=input.nextDouble();
        double r1_r=input.nextDouble();
        System.out.println("Enter circle2's center x-, y-coordinates and radius:");
        double r2_x=input.nextDouble();
        double r2_y=input.nextDouble();
        double r2_r=input.nextDouble();

        double d=Math.sqrt(Math.pow(r1_x-r2_x,2)+Math.pow(r1_y-r2_y,2));

        if (d<=Math.abs(r1_r-r2_r))
        {
            System.out.println("circle2 is inside circle1");
        }
        else if (d<=r1_r+r2_r)
        {
            System.out.println("circle2 overlaps circle1");
        }
        else
        {
            System.out.println("circle2 does not overlap circle1");
        }
    }
}
