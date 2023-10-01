public class Exercise9_18RegularExpression {
  /**Main method*/
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter numbers separated by spaces: ");
    String s = input.nextLine();

    java.util.Scanner scanner = new java.util.Scanner(s);
    double sum = 0;
    while (scanner.hasNext()) {
      sum += scanner.nextDouble();
    }

    System.out.println("Sum is " + sum);
  }
}
