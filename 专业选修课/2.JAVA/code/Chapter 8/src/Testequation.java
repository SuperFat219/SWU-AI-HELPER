import java.util.Scanner;

public class Testequation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a =input.nextDouble();
        double b =input.nextDouble();
        double c =input.nextDouble();
        double d =input.nextDouble();
        double e =input.nextDouble();
        double f =input.nextDouble();
        equation equation = new equation(a,b,c,d,e,f);
        if (!equation.isSolvable()){
            System.out.println("The equation has no solution");
        }
        else{
            System.out.println("X = "+equation.getX());
            System.out.println("Y = "+equation.getY());
        }

    }
}
