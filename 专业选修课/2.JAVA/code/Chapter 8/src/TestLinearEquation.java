import java.util.Scanner;

public class TestLinearEquation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the endpoints of the first line segment");
        double x1 = input.nextDouble();
        double y1 = input.nextDouble();
        double x2 = input.nextDouble();
        double y2 = input.nextDouble();
        System.out.println("Enter the endpoints of the second line segment");
        double x3 = input.nextDouble();
        double y3 = input.nextDouble();
        double x4 = input.nextDouble();
        double y4 = input.nextDouble();
        double a = (y2 - y1) / (x2 - x1);
        double b = -1;
        double e = ((y2 - y1) / (x2 - x1)) * x2 - y2;
        double c = ((y4 - y3) / (x4 - x3));
        double d = -1;
        double f = ((y4 - y3) / (x4 - x3)) * x4 - y4;
        equation equation = new equation(a, b, c, d, e, f);
        double x = equation.getX();
        double y = equation.getY();
        System.out.printf("The intersecting point is:(%.1f,%.1f)", x, y);
    }
}
//y-y2=((y2-y1)/(x2-x1))(x-x2)
//((y2-y1)/(x2-x1))x -y=((y2-y1)/(x2-x1))x2-y2