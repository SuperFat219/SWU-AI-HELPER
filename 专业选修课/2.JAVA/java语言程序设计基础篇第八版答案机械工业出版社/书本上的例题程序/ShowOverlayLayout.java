import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowOverlayLayout extends JApplet {
  private JButton jbt1 = new JButton("Button 1");
  private JButton jbt2 = new JButton("Button 2");

  private JTextField jtfButton1AlignmentX = new JTextField(4);
  private JTextField jtfButton1AlignmentY = new JTextField(4);
  private JTextField jtfButton2AlignmentX = new JTextField(4);
  private JTextField jtfButton2AlignmentY = new JTextField(4);
  private JComboBox jcboButton1Opaque = new JComboBox(
    new Object[]{new Boolean(true), new Boolean(false)});

  // Panel p1 to hold two buttons
  private JPanel p1 = new JPanel();

  public ShowOverlayLayout() {
    // Add two buttons to p1 of OverlayLayout
    p1.setLayout(new OverlayLayout(p1));
    p1.add(jbt1);
    p1.add(jbt2);

    JPanel p2 = new JPanel();
    p2.setLayout(new GridLayout(5, 1));
    p2.add(new JLabel("Button 1's alignmentX"));
    p2.add(new JLabel("Button 1's alignmentY"));
    p2.add(new JLabel("Button 2's alignmentX"));
    p2.add(new JLabel("Button 2's alignmentY"));
    p2.add(new JLabel("Button 1's opaque"));

    JPanel p3 = new JPanel();
    p3.setLayout(new GridLayout(5, 1));
    p3.add(jtfButton1AlignmentX);
    p3.add(jtfButton1AlignmentY);
    p3.add(jtfButton2AlignmentX);
    p3.add(jtfButton2AlignmentY);
    p3.add(jcboButton1Opaque);

    JPanel p4 = new JPanel();
    p4.setLayout(new BorderLayout(4, 4));
    p4.add(p2, BorderLayout.WEST);
    p4.add(p3, BorderLayout.CENTER);

    add(p1, BorderLayout.CENTER);
    add(p4, BorderLayout.WEST);

    jtfButton1AlignmentX.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbt1.setAlignmentX(
          Float.parseFloat(jtfButton1AlignmentX.getText()));
        p1.revalidate(); // Cause the components to be rearranged
        p1.repaint(); // Cause the viewing area to be repainted
      }
    });
    jtfButton1AlignmentY.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbt1.setAlignmentY(
          Float.parseFloat(jtfButton1AlignmentY.getText()));
        p1.revalidate(); // Cause the components to be rearranged
        p1.repaint(); // Cause the viewing area to be repainted
      }
    });
    jtfButton2AlignmentX.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbt2.setAlignmentX(
          Float.parseFloat(jtfButton2AlignmentX.getText()));
        p1.revalidate(); // Cause the components to be rearranged
        p1.repaint(); // Cause the viewing area to be repainted
      }
    });
    jtfButton2AlignmentY.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbt2.setAlignmentY(
          Float.parseFloat(jtfButton2AlignmentY.getText()));
        p1.revalidate(); // Cause the components to be rearranged
        p1.repaint(); // Cause the viewing area to be repainted
      }
    });
    jcboButton1Opaque.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbt1.setOpaque(((Boolean)(jcboButton1Opaque.
          getSelectedItem())).booleanValue());
        p1.revalidate(); // Cause the components to be rearranged
        p1.repaint(); // Cause the viewing area to be repainted
      }
    });
  }

  public static void main(String[] args) {
    ShowOverlayLayout applet = new ShowOverlayLayout();
    JFrame frame = new JFrame();
    //EXIT_ON_CLOSE == 3
    frame.setDefaultCloseOperation(3);
    frame.setTitle("ShowOverlayLayout");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(400,320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }
}
