import java.util.Arrays;
import java.util.Scanner;

/**
 * Time:2021.11.27
 * 6.25
 */
public class equation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("ax^2+bx+c=0,Enter the parameters");
        double[] eqn = new double[3];
        for (int i = 0; i < 3; i++) {
            eqn[i] = input.nextDouble();
        }
        System.out.printf("the equation is %.2fx^2+%.2fx+%.2f=0\n", eqn[0], eqn[1], eqn[2]);
        double[] roots = new double[2];
        int rootNum = solveQuadratic(eqn, roots);
        if (rootNum == 2) {
            System.out.printf("the roots of the equation are %f and %f", roots[0], roots[1]);
        } else if (rootNum == 1) {
            System.out.printf("the equation only have one root:%f", roots[0]);
        } else {
            System.out.print("the equation have no roots");
        }
//        System.out.println(Arrays.toString(roots));
    }

    /**
     * @param eqn   方程系数
     * @param roots 方程的根
     * @return 根的个数
     */
    public static int solveQuadratic(double[] eqn, double[] roots) {
        double a = eqn[0];
        double b = eqn[1];
        double c = eqn[2];
        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            roots[0] = (-b + Math.sqrt(delta)) / (2 * a);
            roots[1] = (-b - Math.sqrt(delta)) / (2 * a);
            return 2;
        } else if (delta == 0) {
            roots[0] = (-b + Math.sqrt(delta)) / (2 * a);
        }
        return 0;


    }
}
