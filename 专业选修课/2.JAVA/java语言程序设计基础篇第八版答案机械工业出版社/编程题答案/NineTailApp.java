import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class NineTailApp extends JApplet {
  // Create the initial board
  private InitialNodePanel initialNodePanel = new InitialNodePanel();
  private JButton jbtSolve = new JButton("Solve");
  private JButton jbtStartOver = new JButton("Start Over");
  // solutionPanel holds a sequeunce of panels for displaying nodes
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
        java.util.List<NineTailModel.Node> list =
          model.getShortestPath(new NineTailModel.Node(
            initialNodePanel.getMatrix()));

        // Display nodes in the shortest path
        for (NineTailModel.Node node: list) {
          solutionPanel.add(new NodePanel(node.matrix));
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
    public NodePanel(int[][] matrix) {
      this.setLayout(new GridLayout(3, 3));
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (matrix[i][j] == 0) {
            add(new Cell("H")); // Display H for cell 0
          }
          else {
            add(new Cell("T")); // Display T for cell 1
          }
        }
      }
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

    /** Get a 3 by 3 matrix from GUI cells */
    public int[][] getMatrix() {
      int[][] matrix = new int[3][3];
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (clickableCells[i][j].getText().equals("H")) {
            matrix[i][j] = 0; // 0 is for head
          }
          else {
            matrix[i][j] = 1; // 0 is for tail
          }
        }
      }

      return matrix;
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
