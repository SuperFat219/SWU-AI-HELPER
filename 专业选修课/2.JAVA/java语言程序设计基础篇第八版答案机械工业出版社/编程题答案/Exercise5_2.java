// Exercise5_2.java: Create a method for summarizing digits in an int
public class Exercise5_2 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a number: ");
    int value = input.nextInt();

    System.out.println("The sum of digits for " + value +
      " is " + sumDigits(value));
  }

  public static int sumDigits(long n) {
    int temp = (int)Math.abs(n);
    int sum = 0;

    while (temp != 0) {
      int remainder = temp % 10;
      sum += remainder;
      temp = temp / 10;
    }

    return sum;
  }
}
