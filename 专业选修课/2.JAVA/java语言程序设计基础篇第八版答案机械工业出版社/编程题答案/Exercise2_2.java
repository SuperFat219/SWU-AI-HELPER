import java.util.Scanner;

public class Exercise2_2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter radius of the cylinder
    System.out.print("Enter radius of the cylinder: ");
    double radius = input.nextDouble();

    // Enter length of the cylinder
    System.out.print("Enter length of the cylinder: ");
    double length = input.nextDouble();

    double volume = radius * radius * 3.14159 * length;

    System.out.println("The volume of the cylinder is " + volume);
  }
}
