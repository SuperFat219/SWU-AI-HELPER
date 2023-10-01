import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise18_24 extends JApplet {
  int[][] set1 = { {1, 3, 5, 7}, {9, 11, 13, 15}, {17, 19, 21, 23}, {25, 27, 29,
    31}
  };

  int[][] set2 = { {2, 3, 6, 7}, {10, 11, 14, 15}, {18, 19, 22, 23}, {26, 27,
    30, 31}
  };

  int[][] set3 = { {4, 5, 6, 7}, {12, 13, 14, 15}, {20, 21, 22, 23}, {28, 29,
    30, 31}
  };

  int[][] set4 = { {8, 9, 10, 11}, {12, 13, 14, 15}, {24, 25, 26, 27}, {28, 29,
    30, 31}
  };

  int[][] set5 = { {16, 17, 18, 19}, {20, 21, 22, 23}, {24, 25, 26, 27}, {28,
    29, 30, 31}
  };

  NumberPanel p1 = new NumberPanel(set1);
  NumberPanel p2 = new NumberPanel(set2);
  NumberPanel p3 = new NumberPanel(set3);
  NumberPanel p4 = new NumberPanel(set4);
  NumberPanel p5 = new NumberPanel(set5);

  JTextField jtfBirthDate = new JTextField(4);

  public Exercise18_24() {
    JPanel fiveNumberSets =
      new JPanel(new GridLayout(1, 5, 10, 10));
    fiveNumberSets.add(p1);
    fiveNumberSets.add(p2);
    fiveNumberSets.add(p3);
    fiveNumberSets.add(p4);
    fiveNumberSets.add(p5);

    JButton jbt = new JButton("Guess Birthday");
    JPanel displayBirthDate = new JPanel();
    displayBirthDate.add(jbt);
    displayBirthDate.add(jtfBirthDate);

    jbt.addActionListener(new ActionListenerClass());

    this.add(fiveNumberSets, BorderLayout.CENTER);
    this.add(new JLabel(
      "Check the boxes if your birthday is in these sets",
      JLabel.CENTER),
             BorderLayout.NORTH);
    this.add(displayBirthDate, BorderLayout.SOUTH);
  }

  class ActionListenerClass implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int date = 0;
      if (p1.isChecked()) {
        date += set1[0][0];
      }

      if (p2.isChecked()) {
        date += set2[0][0];
      }

      if (p3.isChecked()) {
        date += set3[0][0];
      }

      if (p4.isChecked()) {
        date += set4[0][0];
      }

      if (p5.isChecked()) {
        date += set5[0][0];
      }

      jtfBirthDate.setText(date + "");
    }
  }


  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise18_24");
    JApplet applet = new Exercise18_24();

    frame.add(applet);
    frame.setSize(600, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}


class NumberPanel extends JPanel {
  private int[][] numbers;
  private JCheckBox jchkInTheSet =
    new JCheckBox();

  public NumberPanel(int[][] numbers) {
    this.numbers = numbers;

    JPanel panel = new JPanel(new GridLayout(4, 4));
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        panel.add(new JLabel(numbers[i][j] + ""));
      }
    }

    this.setLayout(new BorderLayout());
    this.add(panel, BorderLayout.CENTER);
    this.add(jchkInTheSet, BorderLayout.SOUTH);
    this.setBorder(new LineBorder(Color.red));
  }

  public boolean isChecked() {
    return jchkInTheSet.isSelected();
  }
}
