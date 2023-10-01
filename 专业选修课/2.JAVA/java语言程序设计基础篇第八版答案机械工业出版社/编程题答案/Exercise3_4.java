import javax.swing.*;

public class Exercise3_4 {
  public static void main(String[] args) {
    int number1 = (int)(System.currentTimeMillis() % 100);
    int number2 = (int)(System.currentTimeMillis() * 7 % 100);

    String resultString = JOptionPane.showInputDialog
      ("What is " + number1 + " + " + number2 + "?");

    int result = Integer.parseInt(resultString);

    JOptionPane.showMessageDialog(null,
      number1 + " + " + number2 + " = " + result + " is " +
      (number1 + number2 == result));
  }
}
