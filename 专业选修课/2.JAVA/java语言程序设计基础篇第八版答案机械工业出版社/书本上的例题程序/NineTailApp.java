import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class NineTailApp extends JApplet {
  // Create the initial board
  private InitialNodePanel initialNodePanel = new InitialNodePanel();
  private JButton jbtSolve = new JButton("Solve");
  private JButton jbtStartOver = new JButton("Start Over");
  // solutionPanel holds a sequence of panels for displaying nodes
  private JPanel solutionPanel =
    new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
  private NineTailModel model = new NineTailModel();

  /** Initialize UI */
  public NineTailApp() {
    // Place solutionPanel in a scroll pane
    solutionPanel.add(initialNodePanel);
    add(new JScrollPane(solutionPanel), BorderLayout.CENTER);

    // buttonPanel holds two buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(jbtSolve);
    buttonPanel.add(jbtStartOver);
    add(buttonPanel, BorderLayout.SOUTH);

    // Listener for the Solve button
    jbtSolve.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        solutionPanel.removeAll();

        // Get a shortest path
        java.util.List<Integer> list =
          model.getShortestPath(NineTailModel.getIndex(
            initialNodePanel.getNode()));

        // Display nodes in the shortest path
        for (int nodeIndex: list) {
          solutionPanel.add(
            new NodePanel(NineTailModel.getNode(nodeIndex)));
        }

        solutionPanel.revalidate();
      }
    });

    // Listener for the Start Over button
    jbtStartOver.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        solutionPanel.removeAll();
        solutionPanel.add(initialNodePanel); // Display initial node
        solutionPanel.repaint();
      }
    });
  }

  /** An inner class for displaying a node on a panel */
  static class NodePanel extends JPanel {
    public NodePanel(char[] node) {
      this.setLayout(new GridLayout(3, 3));
      
      for (int i = 0; i < 9; i++)
        add(new Cell(node[i] + ""));
    }
  }

  /** An inner class for displaying a cell */
  static class Cell extends JLabel {
    public Cell(String s) {
      this.setBorder(new LineBorder(Color.black, 1)); // Cell border
      this.setHorizontalAlignment(JLabel.CENTER);
      this.setFont(new Font("TimesRoman", Font.BOLD, 20));
      setText(s);
    }
  }

  /** An inner class for displaying the initial node */
  static class InitialNodePanel extends JPanel {
    // Each cell represents a coin, which can be flipped
    ClickableCell[][] clickableCells = new ClickableCell[3][3];

    public InitialNodePanel() {
      this.setLayout(new GridLayout(3, 3));

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          add(clickableCells[i][j] = new ClickableCell("H"));
        }
      }
    }

    /** Get an array of characters for a node from a GUI node */
    public char[] getNode() {
      char[] node = new char[9];
      int k = 8;
      for (int i = 0; i < 3; i++) 
        for (int j = 0; j < 3; j++) {
          node[k] = clickableCells[i][j].getText().charAt(0);
          k--;
        }
      
      return node;
    }
  }

  /** An inner class for displaying a clickable cell */
  static class ClickableCell extends Cell {
    public ClickableCell(String s) {
      super(s);
      addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          if (getText().equals("H")) {
            setText("T"); // Flip from H to T
          }
          else {
            setText("H"); // Flip from T to H
          }
        }
      });
    }
  }
  
  /** This main method enables the applet to run as an application */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Nine Tail Problem");

    // Create an instance of the applet
    NineTailApp applet = new NineTailApp();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Display the frame
    frame.setSize(300, 180);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
