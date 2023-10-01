import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exercise18_8 extends JApplet {
  private JTextField jtf = new JTextField(10);
  private boolean newNumber = true;
  private int result = 0;
  private String op = "=";

  public void init() {
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());

    JPanel westPanel = new JPanel();
    westPanel.setLayout(new GridLayout(5, 0));
    westPanel.add(new JButton("   "));
    westPanel.add(new JButton("MC"));
    westPanel.add(new JButton("MR"));
    westPanel.add(new JButton("MS"));
    westPanel.add(new JButton("M+"));

    Panel centerPanel = new Panel();
    centerPanel.setLayout(new BorderLayout());
    Panel p1 = new Panel();
    Panel p2 = new Panel();

    p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
    p1.add(new JButton("Back"));
    p1.add(new JButton("CE"));
    p1.add(new JButton("C"));

    p2.setLayout(new GridLayout(4, 5));
    JButton bt;
    p2.add(bt = new JButton("7"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("8"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("9"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("/"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("sqrt"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("4"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("5"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("6"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("*"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("%"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("1"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("2"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("3"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("-"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("1/x"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("0"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("+/-"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("."));
    p2.add(bt = new JButton("+"));
    bt.addActionListener(new Listener());
    p2.add(bt = new JButton("="));
    bt.addActionListener(new Listener());

    centerPanel.add(p2, BorderLayout.CENTER);
    centerPanel.add(p1, BorderLayout.NORTH);
    p.add(centerPanel, BorderLayout.CENTER);
    p.add(westPanel, BorderLayout.WEST);

    setLayout(new BorderLayout());
    add(p, BorderLayout.CENTER);
    add(jtf, BorderLayout.NORTH);
  }

  class Listener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
	    String actionCommand = e.getActionCommand();
	    if ('0' <= actionCommand.charAt(0) &&
	      actionCommand.charAt(0) <= '9') {
	      if (newNumber) {
	        jtf.setText(actionCommand);
	        newNumber = false;
	      }
	      else {
	        jtf.setText(jtf.getText() + actionCommand);
	      }
	    }
	    else
	      if (newNumber) {
	        if (actionCommand.equals("-")) {
	          jtf.setText("-");
	          newNumber = false;
	        }
	        else
	          op = actionCommand;
	      }
	      else {
	        execute();
	        op = actionCommand;
	      }
	    }
	
	  void execute() {
	    int number = new Integer(jtf.getText()).intValue();
	    System.out.println("number " + op);
	    switch (op.charAt(0)) {
	      case '+': result += number; break;
	      case '-': result -= number; break;
	      case '*': result *= number; break;
	      case '/': result /= number; break;
	      case '%': result %= number; break;
	      case '=': result = number;
	    }
	    System.out.println("result "+result);
	    jtf.setText(new Integer(result).toString());
	    newNumber = true;
	  }
  }

    /**This main method enables the applet to run as an application*/
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("Exercise18_8");

    // Create an instance of the applet
    Exercise18_8 applet = new Exercise18_8();

    // Add the applet instance to the frame
    frame.add(applet, BorderLayout.CENTER);

    // Invoke init() and start()
    applet.init();
    applet.start();

    // Display the frame
    frame.setSize(300, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
