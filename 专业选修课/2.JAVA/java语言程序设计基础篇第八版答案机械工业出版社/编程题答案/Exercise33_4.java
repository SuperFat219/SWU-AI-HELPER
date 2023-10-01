// Exercise33_4.java: Use CardLayout
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Exercise33_4 extends JApplet implements ActionListener {
  private CardLayout queue = new CardLayout();
  private JPanel cardPanel = new JPanel();
  private JComboBox jcboIntRational = new JComboBox(new Object[] {
    "Integer Operation", "Rational Operation"});

  public Exercise33_4() {
    //create intPanel for integer arithmetic
    JPanel intPanel = new IntPanel();

    //create rationalPanel for rational arithmetic
    JPanel rationalPanel = new RationalPanel();

    cardPanel.setLayout(queue);
    cardPanel.add(intPanel, "Integer");
    cardPanel.add(rationalPanel, "Rational");

    //set FlowLayout in the frame
    setLayout(new BorderLayout());
    add(jcboIntRational, BorderLayout.NORTH);
    add(cardPanel, BorderLayout.CENTER);

    jcboIntRational.addActionListener(this);
  }

  //handling menu selection
  public void actionPerformed(ActionEvent e) {
    if (jcboIntRational.getSelectedItem().equals("Integer Operation")) {
      queue.first(cardPanel);
    }
    else if (jcboIntRational.getSelectedItem().equals(
      "Rational Operation")) {
      queue.last(cardPanel);
    }
  }

  public static void main(String[] args) {
    Exercise33_4 applet = new Exercise33_4();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setTitle("Exercise33_4");
    frame.add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400, 320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }
}

class IntPanel extends CalculationPanel {
  IntPanel() {
    super("Integer Calculation");
  }

  void add() {
    int result = getNum1() + getNum2();
    //set result in JTextField tf3
    tfResult.setText(String.valueOf(result));
  }

  void subtract() {
    int result = getNum1() - getNum2();
    //set result in JTextField tf3
    tfResult.setText(String.valueOf(result));
  }

  void multiply() {
    int result = getNum1() * getNum2();
    //set result in JTextField tfResult
    tfResult.setText(String.valueOf(result));
  }

  void divide() {
    int result = getNum1() / getNum2();
    //set result in JTextField tfResult
    tfResult.setText(String.valueOf(result));
  }

  private int getNum1() {
    //use trim() to trim eztraneous space in the text field
    int num1 = Integer.parseInt(tfNum1.getText().trim());
    return num1;
  }

  private int getNum2() {
    //use trim() to trim eztraneous space in the text field
    int num2 = Integer.parseInt(tfNum2.getText().trim());
    return num2;
  }
}


class RationalPanel extends CalculationPanel {
  RationalPanel() {
    super("Rational Calculation");
  }

  void add() {
    Rational num1 = getNum1();
    Rational num2 = getNum2();
    Rational result = num1.add(num2);

    //set result in JTextField tfResult
    tfResult.setText(result.toString());
  }

  void subtract() {
    Rational num1 = getNum1();
    Rational num2 = getNum2();
    Rational result = num1.subtract(num2);

    //set result in JTextField tfResult
    tfResult.setText(result.toString());
  }

  void multiply() {
    Rational num1 = getNum1();
    Rational num2 = getNum2();
    Rational result = num1.multiply(num2);

    //set result in JTextField tfResult
    tfResult.setText(result.toString());
  }

  void divide() {
    Rational num1 = getNum1();
    Rational num2 = getNum2();
    Rational result = num1.divide(num2);

    //set result in JTextField tfResult
    tfResult.setText(result.toString());
  }

  Rational getNum1() {
    StringTokenizer st1 = new
      StringTokenizer(tfNum1.getText().trim(), "/");
    int numer1 = Integer.parseInt(st1.nextToken());
    int denom1 = Integer.parseInt(st1.nextToken());
    return new Rational(numer1, denom1);
  }

  Rational getNum2() {
    StringTokenizer st2 = new
      StringTokenizer(tfNum2.getText().trim(), "/");
    int numer2 = Integer.parseInt(st2.nextToken());
    int denom2 = Integer.parseInt(st2.nextToken());
    return new Rational(numer2, denom2);
  }
}


/*design a generic calculation user interface for int and
  rational arithmetic*/
abstract class CalculationPanel extends JPanel implements
  ActionListener {
  private JPanel p0 = new JPanel();
  private JPanel p1 = new JPanel();
  private JPanel p2 = new JPanel();
  JTextField tfNum1, tfNum2, tfResult;
  private JButton jbtAdd, jbtSub, jbtMul, jbtDiv;

  public CalculationPanel(String title) {
    p0.add(new JLabel(title));

    //add labels and text fields
    p1.setLayout(new FlowLayout());
    p1.add(new JLabel("Number 1"));
    p1.add(tfNum1 = new JTextField(" ", 3));
    p1.add(new JLabel("Number 2"));
    p1.add(tfNum2 = new JTextField(" ", 3));
    p1.add(new JLabel("Result"));
    p1.add(tfResult = new JTextField(" ", 4));
    tfResult.setEditable(false);

    //set FlowLayout for p2
    JPanel p2 = new JPanel();
    p2.setLayout(new FlowLayout());
    p2.add(jbtAdd = new JButton("Add"));
    p2.add(jbtSub = new JButton("Subtract"));
    p2.add(jbtMul = new JButton("Multiply"));
    p2.add(jbtDiv = new JButton("Divide"));

    //add panels into CalculationPanel
    setLayout(new BorderLayout());
    add("North", p0);
    add("Center", p1);
    add("South", p2);

    //register listener for source objects
    jbtAdd.addActionListener(this);
    jbtSub.addActionListener(this);
    jbtMul.addActionListener(this);
    jbtDiv.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    String actionCommand = e.getActionCommand();
    if (e.getSource() instanceof JButton) {
      if ("Add".equals(actionCommand)) {
        add();
      }
      else if ("Subtract".equals(actionCommand)) {
        subtract();
      }
      else if ("Multiply".equals(actionCommand)) {
        multiply();
      }
      else if ("Divide".equals(actionCommand)) {
        divide();
      }
    }
  }

  abstract void add();

  abstract void subtract();

  abstract void multiply();

  abstract void divide();
}
