import javax.swing.JOptionPane;

public class Exercise2_1WithDialogBox  {
  // Main method
  public static void main(String[] args) {
    // Enter a temperatur in Fahrenheit
    String celsiusString = JOptionPane.showInputDialog(null,
      "Enter a temperature in Celsius:",
      "Exercise2_1 Input", JOptionPane.QUESTION_MESSAGE);

    // Convert string to double
    double celsius =  Double.parseDouble(celsiusString);

    // Convert it to Celsius
    double fahrenheit = (9.0 / 5) * celsius + 32;

    // Display the result
    JOptionPane.showMessageDialog(null, "The temperature is " +
      fahrenheit + " in Fahrenheit");
  }
}
