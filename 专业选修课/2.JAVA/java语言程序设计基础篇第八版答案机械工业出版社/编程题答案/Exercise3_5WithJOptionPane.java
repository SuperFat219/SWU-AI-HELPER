import javax.swing.*;

public class Exercise3_5WithJOptionPane {
  public static void main(String[] args) {
    int number1 = (int)(System.currentTimeMillis() % 10);
    int number2 = (int)(System.currentTimeMillis() * 7 % 10);
    int number3 = (int)(System.currentTimeMillis() * 3 % 10);

    String answerString = JOptionPane.showInputDialog
      ("What is " + number1 + " + " + number2 + " + " +
       number3 + "?");

    int answer = Integer.parseInt(answerString);

    JOptionPane.showMessageDialog(null,
      number1 + " + " + number2 + " + " + number3 + " = " + answer + " is " +
      (number1 + number2 + number3 == answer));
  }
}
