import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Exercise18_26 extends JApplet {
  private JRadioButton jrbAdd = new JRadioButton("Add");
  private JRadioButton jrbSubtract = new JRadioButton("Subtract");
  private JRadioButton jrbMultiply = new JRadioButton("Multiply");
  private JRadioButton jrbDivide = new JRadioButton("Divide");

  private JRadioButton jrbLevel1 = new JRadioButton("Numbers from 0 to 5");
  private JRadioButton jrbLevel2 = new JRadioButton("Numbers from 3 to 9");
  private JRadioButton jrbLevel3 = new JRadioButton("Numbers from 0 to 20");
  private JRadioButton jrbLevel4 = new JRadioButton("Any two digits");

  private JLabel jlblQuestion = new JLabel("Question will be shown ", JLabel.RIGHT);
  private JTextField jtfAnswer = new JTextField();

  private JButton jbtStart = new JButton("Start");
  private JButton jbtStop = new JButton("Stop");

  private JLabel jlblCorrectCount = new JLabel("Correct count will be shown");
  private JLabel jlblTimeSpent = new JLabel("Time spent will be shown");

  public Exercise18_26() {
    JPanel jpTypeSetting = new JPanel(new GridLayout(4, 1));
    jpTypeSetting.setBorder(BorderFactory.createTitledBorder("Choose a type"));
    jpTypeSetting.add(jrbAdd);
    jpTypeSetting.add(jrbSubtract);
    jpTypeSetting.add(jrbMultiply);
    jpTypeSetting.add(jrbDivide);

    JPanel jpLevelSetting = new JPanel(new GridLayout(4, 1));
    jpLevelSetting.setBorder(BorderFactory.createTitledBorder("Choose a level"));
    jpLevelSetting.add(jrbLevel1);
    jpLevelSetting.add(jrbLevel2);
    jpLevelSetting.add(jrbLevel3);
    jpLevelSetting.add(jrbLevel4);

    JPanel jpSetting = new JPanel(new GridLayout(1, 2));
    jpSetting.add(jpTypeSetting);
    jpSetting.add(jpLevelSetting);

    JPanel jpQuestion = new JPanel(new GridLayout(2, 2));
    jpQuestion.add(new JLabel("Question:"));
    jpQuestion.add(new JLabel("Answer:"));
    jpQuestion.add(jlblQuestion);
    jpQuestion.add(jtfAnswer);

    JPanel jpButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    jpButton.add(jbtStart);
    jpButton.add(jbtStop);

    JPanel jpLabel = new JPanel(new GridLayout(1, 2));
    jpLabel.add(jlblCorrectCount);
    jpLabel.add(jlblTimeSpent);
    jlblCorrectCount.setBorder(BorderFactory.createTitledBorder("Correct Count"));
    jlblTimeSpent.setBorder(BorderFactory.createTitledBorder("Time Spent"));

    JPanel jpResult = new JPanel(new BorderLayout());
    jpResult.add(jpButton, BorderLayout.NORTH);
    jpResult.add(jpLabel, BorderLayout.CENTER);

    this.add(jpSetting, BorderLayout.NORTH);
    this.add(jpQuestion, BorderLayout.CENTER);
    this.add(jpResult, BorderLayout.SOUTH);

    jbtStart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jlblQuestion.setText("3 + 7 = ");
        jlblCorrectCount.setText("12");
        jlblTimeSpent.setText("24 seconds");
      }
      });

    jbtStop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

      }
      });
  }

  class SolveActionListenerClass implements ActionListener {
    public void actionPerformed(ActionEvent e) {

    }
  }


  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise18_26");
    JApplet applet = new Exercise18_26();
    frame.add(applet);
    frame.setSize(350, 270);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
