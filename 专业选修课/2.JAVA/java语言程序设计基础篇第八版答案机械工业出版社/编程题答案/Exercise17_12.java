import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise17_12 extends JFrame {
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel1 = new JPanel();
  JTextArea jta = new JTextArea();
  TitledBorder titledBorder1;
  JRadioButton jrbWrapWords = new JRadioButton();
  JRadioButton jrbWrapCharacters = new JRadioButton();

  // Create a button group
  ButtonGroup btg = new ButtonGroup();
  JCheckBox jchkWrap = new JCheckBox();

  //Component initialization
  public Exercise17_12() {
    titledBorder1 = new TitledBorder("");
    this.setSize(new Dimension(400, 300));
    jta.setText("jTextArea1");
    jPanel1.setBorder(titledBorder1);
    titledBorder1.setTitle("Wrap Options");
    jrbWrapWords.setText("Wrap Words");
    jrbWrapWords.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jrbWrapWords_actionPerformed(e);
      }
    });
    jrbWrapCharacters.setText("Wrap Characters");
    jrbWrapCharacters.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jrbWrapCharacters_actionPerformed(e);
      }
    });
    jchkWrap.setText("Wrap");
    jchkWrap.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        jchkWrap_actionPerformed(e);
      }
    });
    add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jta, null);
    add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jchkWrap, null);
    jPanel1.add(jrbWrapWords, null);
    jPanel1.add(jrbWrapCharacters, null);

    // Group radio buttons
    btg.add(jrbWrapWords);
    btg.add(jrbWrapCharacters);
  }

  //Main method
  public static void main(String[] args) {
    Exercise17_12 frame = new Exercise17_12();
    frame.setTitle("Exercise17_12");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 320);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);
  }

  void jchkWrap_actionPerformed(ActionEvent e) {
    jta.setLineWrap(jchkWrap.isSelected());

    if (jchkWrap.isSelected()) {
      jrbWrapWords.setEnabled(true);
      jrbWrapCharacters.setEnabled(true);
    }
    else {
      jrbWrapWords.setEnabled(false);
      jrbWrapCharacters.setEnabled(false);
    }
  }

  void jrbWrapWords_actionPerformed(ActionEvent e) {
    jta.setWrapStyleWord(jrbWrapWords.isSelected());
    jta.revalidate();
  }

  void jrbWrapCharacters_actionPerformed(ActionEvent e) {
    jta.setWrapStyleWord(!jrbWrapCharacters.isSelected());
    jta.revalidate();
  }
}

