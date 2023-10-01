import java.util.Scanner; 

public class UseMathQuizClass {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Create a test for John Doe
    MathQuiz test1 = new MathQuiz("John Doe");
    TwoNumbers pair = new TwoNumbers();

    while (test1.getCorrectCount() < 3) {
      pair.setNewNumbers(); // Generate new number1 and number2
      int number1 = pair.getNumber1();
      int number2 = pair.getNumber2();

      // Prompt the student to answer “what is number1 – number2?”
      System.out.print("What is " + number1 +
        " - " + number2 + "? ");
      int answer = input.nextInt();
      test1.increaseTotalCount();
      if (number1 - number2 == answer) {
        System.out.println("You are correct!");
        test1.increaseCorrectCount();
      }
      else {
        System.out.println("Your answer is wrong.\n" +
          number1 + " - " + number2 + " should be " +
          (number1 - number2));
      }
    }

    System.out.println("For user " + test1.getUser() +
      ", the correct count is " + test1.getCorrectCount() +
      " out of  " + test1.getTotalCount() + " questions.\n");
  }
}
