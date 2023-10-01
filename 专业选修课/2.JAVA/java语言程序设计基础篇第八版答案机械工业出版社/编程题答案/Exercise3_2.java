public class Exercise3_2 {
  /**Main method*/
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Prompt the user to enter an integer
    System.out.print("Enter an integer: ");
    int number = input.nextInt();

    // Display results
    System.out.println("Is " + number + " an even number? " +
      (number % 2 == 0));
  }
}
