public class Exercise2_8 {
  public static void main(String args[]) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    // Enter an ASCII code
    System.out.print("Enter an ASCII code: ");
    int code = input.nextInt();

    // Display result
    System.out.println("The character for ASCII code "
      + code + " is " + (char)code);
  }
}
