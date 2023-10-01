import java.awt.*;

import javax.swing.*;

public class Exercise12_10 extends JFrame {
  public static void main(String[] args) {
    Exercise12_10 frame = new Exercise12_10();
    frame.setSize(200, 200);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise12_10");
    frame.setVisible(true);
  }

  public Exercise12_10() {
    // Create panel p1 add three buttons
    JPanel p1 = new JPanel(new GridLayout(8, 8));   
    add(p1);    

    JButton[][] buttons = new JButton[8][8];
    boolean isWhite = true;   
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        buttons[i][j] = new JButton();
        p1.add(buttons[i][j]); 
        if (isWhite) {
          buttons[i][j].setBackground(Color.WHITE);
          isWhite = false;
        }
        else {
          buttons[i][j].setBackground(Color.BLACK);
          isWhite = true;
        }
      }

      if (i % 2 == 0)
        isWhite = false; 
      else
        isWhite = true;
    }    
  }
}
