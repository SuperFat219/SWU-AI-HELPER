import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Exercise25_18 extends JApplet {
  private Stack<Integer> stack = new Stack<Integer>();
  private StackView view = new StackView();
  private JButton jbtInsert = new JButton("Insert (push)");
  private JButton jbtDelete = new JButton("Delete (pop)");
  private JTextField jtfNumber = new JTextField(2);
  
  public Exercise25_18() {    
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter a value: "));
    panel.add(jtfNumber);
    panel.add(jbtInsert);
    panel.add(jbtDelete);
    
    add(view);  
    add(panel, BorderLayout.SOUTH);

    jbtInsert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stack.push(Integer.parseInt(jtfNumber.getText()));
        view.repaint();
      }
    });
    
    jbtDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stack.pop(); 
        view.repaint();
      }
    });
  }
  
  public class StackView extends JPanel {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      if (stack.size() == 0) {
        g.drawString("stack is empty", startingX, startingY);
      }
      else {
        g.drawString("top", startingX, startingY);        
        int x = startingX + 10;
        int y = startingY + 10;        
        ArrayList<Integer> list = new ArrayList<Integer>(stack);
        
        for (int i = list.size() - 1; i >= 0; i--) {
          g.drawRect(x, y, boxWidth, boxHeight);
          g.drawString(list.get(i) + "", x + 10, y + 15);
          x = x + boxWidth;
        }
      }
    }
  }
  
  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new Exercise25_18());
    frame.setTitle("Exercise25_18");
    frame.setSize(350, 130);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}