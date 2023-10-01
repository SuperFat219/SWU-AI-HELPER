import java.util.Scanner;

/*
 * Number:2.21
 * Time:2021.11.07
 */

/**
 * @author yzs
 * @version 1.0
 */
public class area{
    /**
     * Functions:compute the area of the triangle
     * Input:the location of the vertexes
     * Output:the area of the triangle
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter three points for a triangle:");
        double x1 = input.nextDouble();
        double y1 = input.nextDouble();
        double x2 = input.nextDouble();
        double y2 = input.nextDouble();
        double x3 = input.nextDouble();
        double y3 = input.nextDouble();
        double side1=side(x1,y1,x2,y2);
        double side2=side(x1,y1,x3,y3);
        double side3=side(x2,y2,x3,y3);
        double s=(side1+side2+side3)/2;
        double area=Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
        System.out.printf("The area of the triangle is %.1f",area);
    }
    /**
         * compute the distance between two dots
         * @param x1 the location x of the first dot
         * @param y1 the location y of the first dot
         * @param x2 the location x of the second dot
         * @param y2 the location y of the second dot
         * @return the distance between two dots
         */
    public static double side(double x1,double y1,double x2,double y2)
    {
        
        return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
    }
}