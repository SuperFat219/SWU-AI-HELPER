import javax.swing.JOptionPane;

public class Exercise3_10 {
  public static void main(String[] args) {
    // 1. Generate two random single-digit integers
    int number1 = (int)(Math.random() * 10);
    int number2 = (int)(Math.random() * 10);

    // 2. Prompt the student to answer “what is number1 + number2?”
    String answerString = JOptionPane.showInputDialog
      ("What is " + number1 + " + " + number2 + "?");
    int answer = Integer.parseInt(answerString);

    // 4. Grade the annser and display the result
    String replyString;
    if (number1 + number2 == answer)
      replyString = "You are correct!";
    else
      replyString = "Your answer is wrong.\n" + number1 + " + "
        + number2 + " should be " + (number1 + number2);
    JOptionPane.showMessageDialog(null, replyString);
  }
}
