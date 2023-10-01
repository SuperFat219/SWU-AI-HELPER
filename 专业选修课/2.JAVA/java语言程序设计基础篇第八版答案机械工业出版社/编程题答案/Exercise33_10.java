import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise33_10 extends JApplet {
  private FlowLayout flowLayout = new FlowLayout();
  private GridLayout gridLayout = new GridLayout(2, 2);
  private DiagonalLayout diagonalLayout = new DiagonalLayout();

  private JButton jbt1 = new JButton("Button 1");
  private JButton jbt2 = new JButton("Button 2");
  private JButton jbt3 = new JButton("Button 3");
  private JButton jbt4 = new JButton("Button 4");

  private JRadioButton jrbFlowLayout =
    new JRadioButton("FlowLayout");
  private JRadioButton jrbGridLayout =
    new JRadioButton("GridLayout");
  private JRadioButton jrbDiagonalLayout =
    new JRadioButton("DiagonalLayout", true);

  private JPanel jPanel2 = new JPanel();
  private JPanel jPanel3 = new JPanel(new BorderLayout());
  private JPanel jPanel4 = new JPanel(new GridLayout(3, 1));  
  
  private JTextField jtfDiagonalGap = new JTextField(diagonalLayout.getGap() + "");
  private JCheckBox jchMajorDiagonal = new JCheckBox("Major Diagonal");
  private JCheckBox jchLastStretched = new JCheckBox("Last Component Stretched");

  public Exercise33_10() {
    // Set default layout in jPanel2
    jPanel2.setLayout(diagonalLayout);
    jPanel2.add(jbt1);
    jPanel2.add(jbt2);
    jPanel2.add(jbt3);
    jPanel2.add(jbt4);
    jPanel2.setBorder(new LineBorder(Color.black));

    JPanel jPanel1 = new JPanel();
    jPanel1.setBorder(new TitledBorder("Select a Layout Manager"));
    jPanel1.add(jrbFlowLayout);
    jPanel1.add(jrbGridLayout);
    jPanel1.add(jrbDiagonalLayout);

    ButtonGroup buttonGroup1 = new ButtonGroup();
    buttonGroup1.add(jrbFlowLayout);
    buttonGroup1.add(jrbGridLayout);
    buttonGroup1.add(jrbDiagonalLayout);

    JPanel jPanel5 = new JPanel(new BorderLayout());
    jPanel5.add(new Label("Diagonal gap: "), BorderLayout.WEST);    
    jPanel5.add(jtfDiagonalGap);
    
    jPanel4.add(jPanel5);
    jchMajorDiagonal.setSelected(true);
    jPanel4.add(jchMajorDiagonal);
    jPanel4.add(jchLastStretched);
    
    jPanel3.add(jPanel1);
    jPanel3.add(jPanel4, BorderLayout.EAST);
    add(jPanel3, BorderLayout.SOUTH);
    add(jPanel2, BorderLayout.CENTER);
    
    jrbFlowLayout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jPanel2.setLayout(flowLayout);
        jPanel3.remove(jPanel4);
        jPanel2.revalidate();
        jPanel3.revalidate();   
      }
    });
    jrbGridLayout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jPanel2.setLayout(gridLayout);
        jPanel3.remove(jPanel4);
        jPanel2.revalidate();
        jPanel3.revalidate();   
      }
    });
    jrbDiagonalLayout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jPanel2.setLayout(diagonalLayout);
        jPanel3.add(jPanel4, BorderLayout.EAST);
        jPanel2.revalidate();
        jPanel3.revalidate();
      }
    });
    jtfDiagonalGap.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent e) {
	    diagonalLayout.setGap(Integer.parseInt(jtfDiagonalGap.getText().trim()));
	    jPanel2.revalidate();
	  }
	});
    jchMajorDiagonal.addActionListener(new ActionListener() {
  	  public void actionPerformed(ActionEvent e) {
  	    if (jchMajorDiagonal.isSelected())
  	      diagonalLayout.setMajorDiagonal(true);
  	    else
    	  diagonalLayout.setMajorDiagonal(false);
  	    jPanel2.revalidate();
  	  }
  	});
    jchLastStretched.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jchLastStretched.isSelected())
   	      diagonalLayout.setLastFill(true);
   	    else
      	  diagonalLayout.setLastFill(false);
   	    jPanel2.revalidate();
   	  }
    });    
  }

  public static void main(String[] args) {
    Exercise33_10 applet = new Exercise33_10();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Exercise33_10");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(600, 320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
