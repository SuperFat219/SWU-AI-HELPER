import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class CircleController extends JPanel
    implements ActionListener {
  private CircleModel model;
  private JTextField jtfRadius = new JTextField();
  private JComboBox jcboFilled = new JComboBox(new Boolean[]{
    new Boolean(false), new Boolean(true)});
  private JLabel jlblColor = new JLabel();
  private JButton jbtChooseColor = new JButton("...");

  /** Creates new form CircleController */
  public CircleController() {
    // Panel to group labels
    JPanel panel1 = new JPanel();
    panel1.setLayout(new GridLayout(3, 1));
    panel1.add(new JLabel("Radius"));
    panel1.add(new JLabel("Filled"));
    panel1.add(new JLabel("Color"));

    // Panel to group text field, combo box, and another panel
    JPanel panel2 = new JPanel();
    panel2.setLayout(new GridLayout(3, 1));
    panel2.add(jtfRadius);
    panel2.add(jcboFilled);
    JPanel colorPanel = new JPanel();
    colorPanel.setLayout(new BorderLayout());
    jlblColor.setOpaque(true);
    colorPanel.add(jlblColor, BorderLayout.CENTER);
    colorPanel.add(jbtChooseColor, BorderLayout.EAST);
    colorPanel.setBorder(
      new javax.swing.border.LineBorder(Color.red));
    panel2.add(colorPanel);

    setLayout(new BorderLayout());
    add(panel1, BorderLayout.WEST);
    add(panel2, BorderLayout.CENTER);

    // Register listeners
    jtfRadius.addActionListener(this);
    jcboFilled.addActionListener(this);
    jbtChooseColor.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    if (model == null) return; // No model associated yet. Do nothing

    if (e.getSource() == jtfRadius)
      model.setRadius(new Double(jtfRadius.getText()).doubleValue());
    else if (e.getSource() == jcboFilled)
      model.setFilled(
        ((Boolean)jcboFilled.getSelectedItem()).booleanValue());
    else if (e.getSource() == jbtChooseColor) {
      Color selectedColor = JColorChooser.showDialog(this,
        "Choose a Color", jlblColor.getBackground());
      if (selectedColor != null) {
        jlblColor.setBackground(selectedColor);
        model.setColor(selectedColor);
      }
    }
  }

  public void setModel(CircleModel newModel) {
    model = newModel;
  }

  public CircleModel getModel() {
    return model;
  }
}
