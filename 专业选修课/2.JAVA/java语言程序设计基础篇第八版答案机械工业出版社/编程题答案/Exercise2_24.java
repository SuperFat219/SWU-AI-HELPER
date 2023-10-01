import java.util.Scanner;

public class Exercise2_24 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Enter speed v: ");
    double v = input.nextDouble();
    System.out.print("Enter acceleration a: ");
    double a = input.nextDouble();

    double length = v * v / (2 * a);
    
    System.out.println("The minimum runway length for this airplane is " +
        length + " meters");
  }
}