import java.awt.event.*;
import java.util.*;

public class ChartModel {
  private double[] data = {200, 40, 50, 100, 40, 10};
  private String[] dataName = {"CS", "Math", "Chem", "Biol", "Phys", "Buss"};

  private transient Vector actionListeners;

  public ChartModel() {
  }

  public double[] getData() {
    return data;
  }

  public synchronized void removeActionListener(ActionListener l) {
    if (actionListeners != null && actionListeners.contains(l)) {
      Vector v = (Vector) actionListeners.clone();
      v.removeElement(l);
      actionListeners = v;
    }
  }

  public synchronized void addActionListener(ActionListener l) {
    Vector v = actionListeners == null ? new Vector(2) : (Vector) actionListeners.clone();
    if (!v.contains(l)) {
      v.addElement(l);
      actionListeners = v;
    }
  }

  protected void fireActionPerformed(ActionEvent e) {
    if (actionListeners != null) {
      Vector listeners = actionListeners;
      int count = listeners.size();
      for (int i = 0; i < count; i++) {
        ((ActionListener) listeners.elementAt(i)).actionPerformed(e);
      }
    }
  }

  public void setChartData(String[] newDataName, double[] newData) {
    dataName = newDataName;
    data = newData;
    // System.arraycopy(newData, 0, data, 0, newData.length);
    fireActionPerformed(new ActionEvent(this,
      ActionEvent.ACTION_PERFORMED, null));
  }

  public String[] getDataName() {
    return dataName;
  }
}
