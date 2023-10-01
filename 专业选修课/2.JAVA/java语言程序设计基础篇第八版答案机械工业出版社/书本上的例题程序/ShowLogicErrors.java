// ShowLogicErrors.java: The program contains a logic error
public class ShowLogicErrors {
  public static void main(String[] args) {
    // Add number1 to number2
    int number1 = 3;
    int number2 = 3;
    number2 += number1 + number2;
    System.out.println("number2 is " + number2);
  }
}