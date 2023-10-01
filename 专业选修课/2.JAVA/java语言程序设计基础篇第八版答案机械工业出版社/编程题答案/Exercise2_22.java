import java.util.Scanner;

public class Exercise2_22 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Enter the side of the hexagon
    System.out.print("Enter the side: ");
    double side = input.nextDouble();
    
    // Compute the area
    double area = 3 * 1.732 * side * side / 2;
    
    System.out.println("The area of the hexagon is " + area);
  }
}
