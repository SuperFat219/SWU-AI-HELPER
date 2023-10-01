import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Exercise35_9CellRenderer implements ListCellRenderer {
  JPanel listCellPanel = new JPanel();
  FigurePanel figurePanel = new FigurePanel();
  JLabel jlbl = new JLabel(" ", JLabel.LEFT);
  Border lineBorder =
    BorderFactory.createLineBorder(Color.black, 1);
  Border emptyBorder =
    BorderFactory.createEmptyBorder(2, 2, 2, 2);

  public Exercise35_9CellRenderer() {
    figurePanel.setPreferredSize(new Dimension(50, 20));
    figurePanel.setMinimumSize(new Dimension(50, 20));
    listCellPanel.setLayout(new BorderLayout());
    listCellPanel.add(figurePanel, BorderLayout.WEST);
    listCellPanel.add(jlbl, BorderLayout.CENTER);
    listCellPanel.setPreferredSize(new Dimension(180, 40));
  }

  public Component getListCellRendererComponent
    (JList list, Object value, int index, boolean isSelected,
      boolean cellHasFocus) {
    //TODO: implement this javax.swing.ListCellRenderer method;
    String figure = (String)value;
    jlbl.setText(figure);
    jlbl.setForeground(Color.black);

    if (figure.equals("Line"))
      figurePanel.setType(1);
    else if (figure.equals("Rectangle"))
      figurePanel.setType(2);
    else if (figure.equals("Round rectangle"))
      figurePanel.setType(3);
    else if (figure.equals("Oval"))
      figurePanel.setType(4);

    if (isSelected) {
      listCellPanel.setForeground(list.getSelectionForeground());
      listCellPanel.setBackground(Color.white);
//      listCellPanel.setBackground(list.getSelectionBackground());
    }
    else {
      listCellPanel.setForeground(list.getForeground());
      listCellPanel.setBackground(Color.white);
//      listCellPanel.setBackground(list.getBackground());
    }

    if (cellHasFocus)
      listCellPanel.setBorder(lineBorder);
    else
      listCellPanel.setBorder(emptyBorder);

    return listCellPanel;
  }
}
