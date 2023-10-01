// Exercise6_2.java: Enter 10 integers and
// display the numbers in reverse order
public class Exercise6_2 {
  public static void main (String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    int[] num = new int[10];

    for (int i = 0; i < 10; i++) {
      // Read a number
      System.out.print(
        "Read a number: ");

      num[i] = input.nextInt();
    }

    // Display the array
    for (int i = 9; i >= 0; i--) {
      System.out.println(num[i]);
    }
  }
}

