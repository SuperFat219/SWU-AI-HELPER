/*
 *Number:3.28
 *Author:yzs
 *Time:2021.11.13
 */
import java.util.Scanner;

public class triangle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter r1's center x-, y-coordinates, width, and height:");
        double r1_x=input.nextDouble();
        double r1_y=input.nextDouble();
        double r1_w=input.nextDouble();
        double r1_h=input.nextDouble();
        System.out.println("Enter r2's center x-, y-coordinates, width, and height:");
        double r2_x=input.nextDouble();
        double r2_y=input.nextDouble();
        double r2_w=input.nextDouble();
        double r2_h=input.nextDouble();

        double d=Math.sqrt(Math.pow(r1_x-r2_x,2)+Math.pow(r1_y-r2_y,2));
        if (d<r1_w/2 || d<r1_h/2)
        {

            System.out.println("r2 is inside r1");
        }
        else if(d<(r1_w+r2_w)/2 || d<(r1_h+r2_h)/2) 
        {
            System.out.println("r2 overlaps r1");
        }
        else
        {
            System.out.println("r2 does not overlap r1");
        }



    }
}
