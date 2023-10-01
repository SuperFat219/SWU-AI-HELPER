import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise35_12CellRenderer implements ListCellRenderer {
  private JLabel jlbl = new JLabel(" ", JLabel.LEFT);
  private Border lineBorder =
    BorderFactory.createLineBorder(Color.black, 1);
  private Border emptyBorder =
    BorderFactory.createEmptyBorder(2, 2, 2, 2);

  public Exercise35_12CellRenderer() {
  }

  public Component getListCellRendererComponent
    (JList list, Object value, int index, boolean isSelected,
      boolean cellHasFocus) {
    //TODO: implement this javax.swing.ListCellRenderer method;
    String colorString = (String)value;
    jlbl.setText(colorString);

    if (colorString.equals("BLACK"))
      jlbl.setForeground(Color.black);
    else if (colorString.equals("BLUE"))
      jlbl.setForeground(Color.blue);
    else if (colorString.equals("CYAN"))
      jlbl.setForeground(Color.cyan);
    else if (colorString.equals("DARK_GRAY"))
      jlbl.setForeground(Color.darkGray);
    else if (colorString.equals("GRAY"))
      jlbl.setForeground(Color.gray);
    else if (colorString.equals("GREEN"))
      jlbl.setForeground(Color.green);
    else if (colorString.equals("LIGHT_GRAY"))
      jlbl.setForeground(Color.lightGray);
    else if (colorString.equals("MAGENTA"))
      jlbl.setForeground(Color.magenta);
    else if (colorString.equals("ORANGE"))
      jlbl.setForeground(Color.orange);
    else if (colorString.equals("PINK"))
      jlbl.setForeground(Color.pink);
    else if (colorString.equals("RED"))
      jlbl.setForeground(Color.red);
    else if (colorString.equals("WHITE"))
      jlbl.setForeground(Color.white);
    else if (colorString.equals("YELLOW"))
      jlbl.setForeground(Color.yellow);

    if (isSelected) {
      jlbl.setBorder(lineBorder);
      if (colorString.equals("BLACK"))
        jlbl.setForeground(Color.black);
      else if (colorString.equals("BLUE"))
        jlbl.setForeground(Color.blue);
      else if (colorString.equals("CYAN"))
        jlbl.setForeground(Color.cyan);
      else if (colorString.equals("DARK_GRAY"))
        jlbl.setForeground(Color.darkGray);
      else if (colorString.equals("GRAY"))
        jlbl.setForeground(Color.gray);
      else if (colorString.equals("GREEN"))
        jlbl.setForeground(Color.green);
      else if (colorString.equals("LIGHT_GRAY"))
        jlbl.setForeground(Color.lightGray);
      else if (colorString.equals("MAGENTA"))
        jlbl.setForeground(Color.magenta);
      else if (colorString.equals("ORANGE"))
        jlbl.setForeground(Color.orange);
      else if (colorString.equals("PINK"))
        jlbl.setForeground(Color.pink);
      else if (colorString.equals("RED"))
        jlbl.setForeground(Color.red);
      else if (colorString.equals("WHITE"))
        jlbl.setForeground(Color.white);
      else if (colorString.equals("YELLOW"))
        jlbl.setForeground(Color.yellow);
    }
    else {
      jlbl.setBorder(emptyBorder);
    }

    if (cellHasFocus)
      jlbl.setBorder(lineBorder);
    else
      jlbl.setBorder(emptyBorder);

    return jlbl;
  }
}
