import java.util.Scanner;
public class PExercise1 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter two integers: ");
    int value1 = input.nextInt();
    int value2 = input.nextInt();

    int sum = value1 + value2;
    System.out.println("Sum is " + sum);
  }
}