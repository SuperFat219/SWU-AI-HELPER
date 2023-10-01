import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class Exercise25_16 extends JApplet {
  private MyArrayList<Integer> list = new MyArrayList<Integer>();
  private ListView view = new ListView();
  private JButton jbtSearch = new JButton("Search");
  private JButton jbtInsert = new JButton("Insert");
  private JButton jbtDelete = new JButton("Delete");
  private JButton jbtTrimToSize = new JButton("TrimToSize");
  private JTextField jtfNumber = new JTextField(2);
  private JTextField jtfIndex = new JTextField(2);
  
  public Exercise25_16() {    
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter a value: "));
    panel.add(jtfNumber);
    panel.add(new JLabel("Enter an index: "));
    panel.add(jtfIndex);
    panel.add(jbtSearch);
    panel.add(jbtInsert);
    panel.add(jbtDelete);
    panel.add(jbtTrimToSize);
    
    add(view);  
    add(panel, BorderLayout.SOUTH);
    
    jbtSearch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!list.contains(Integer.parseInt(jtfNumber.getText()))) {
          JOptionPane.showMessageDialog(null, "key is not in the list");
        }       
        view.repaint();
      }
    });

    jbtInsert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jtfIndex.getText().trim().length() > 0)
          list.add(Integer.parseInt(jtfIndex.getText()), Integer.parseInt(jtfNumber.getText()));
        else
          list.add(Integer.parseInt(jtfNumber.getText()));
        view.repaint();
      }
    });
    
    jbtDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!list.contains(Integer.parseInt(jtfNumber.getText()))) {
          JOptionPane.showMessageDialog(null, "key is not in the list");
        }       
        else {
          list.remove(new Integer(Integer.parseInt(jtfNumber.getText())));
          view.repaint();
        }
      }
    });
    
    jbtTrimToSize.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        list.trimToSize();
        view.repaint();
      }
    });
  }
  
  public class ListView extends JPanel {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 30;
    private int boxHeight = 20;
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      int x = startingX + 10;
      int y = startingY + 10;        

      g.drawString("size = " + list.size() 
          + " and capacity = " + list.getCapacity(), 
          startingX + 80, startingY);
      if (list.size() == 0) {
        g.drawString("list is empty.", startingX, startingY);
      }
      else {
        g.drawString("array list", startingX, startingY);        
        
        for (int i = 0; i < list.size(); i++) {
          g.drawRect(x, y, boxWidth, boxHeight);
          g.drawString(list.get(i) + "", x + 10, y + 15);
          x = x + boxWidth;
        }
      }
      
      for (int i = list.size(); i < list.getCapacity(); i++) {
        g.drawRect(x, y, boxWidth, boxHeight);
//        g.drawLine(x, y, x + boxWidth, y + boxHeight);
        g.drawLine(x + boxWidth, y, x, y + boxHeight);
        x = x + boxWidth;
      }
    }
  }
  
  public class MyArrayList<E> extends MyAbstractList<E> {
    public static final int INITIAL_CAPACITY = 4;
    private E[] data = (E[])new Object[INITIAL_CAPACITY];

    public int getCapacity() {
      return data.length;
    }
    
    /** Create a default list */
    public MyArrayList() {
    }

    /** Create a list from an array of objects */
    public MyArrayList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new element at the specified index in this list */
    public void add(int index, E e) {
      ensureCapacity();

      // Move the elements to the right after the specified index
      for (int i = size - 1; i >= index; i--)
        data[i + 1] = data[i];

      // Insert new element to data[index]
      data[index] = e;

      // Increase size by 1
      size++;
    }

    /** Create a new larger array, double the current size + 1 */
    private void ensureCapacity() {
      if (size >= data.length) {
        E[] newData = (E[])(new Object[size * 2 + 1]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }

    /** Clear the list */
    public void clear() {
      data = (E[])new Object[INITIAL_CAPACITY];
      size = 0;
    }

    /** Return true if this list contains the element */
    public boolean contains(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return true;

      return false;
    }

    /** Return the element from this list at the specified index */
    public E get(int index) {
      return data[index];
    }

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(E e) {
      for (int i = 0; i < size; i++)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e) {
      for (int i = size - 1; i >= 0; i--)
        if (e.equals(data[i])) return i;

      return -1;
    }

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index) {
      E e = data[index];

      // Shift data to the left
      for (int j = index; j < size - 1; j++)
        data[j] = data[j + 1];

      data[size - 1] = null; // This element is now null
      
      // Decrement size
      size--;

      return e;
    }

    /** Replace the element at the specified position in this list
     *  with the specified element. */
    public E set(int index, E e) {
      E old = data[index];
      data[index] = e;
      return old;
    }

    /** Override toString() to return elements in the list */
    public String toString() {
      StringBuilder result = new StringBuilder("[");

      for (int i = 0; i < size; i++) {
        result.append(data[i]);
        if (i < size - 1) result.append(", ");
      }

      return result.toString() + "]";
    }
    
    /** Trims the capacity to current size */
    public void trimToSize() {
      if (size != data.length) { // If size == capacity, no need to trim
        E[] newData = (E[])(new Object[size]);
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
      }
    }
  }
  
  public abstract class MyAbstractList<E> implements MyList<E> {
    protected int size = 0; // The size of the list

    /** Create a default list */
    protected MyAbstractList() {
    }

    /** Create a list from an array of objects */
    protected MyAbstractList(E[] objects) {
      for (int i = 0; i < objects.length; i++)
        add(objects[i]);
    }

    /** Add a new element at the end of this list */
    public void add(E e) {
      add(size, e);
    }

    /** Return true if this list contains no elements */
    public boolean isEmpty() {
      return size == 0;
    }

    /** Return the number of elements in this list */
    public int size() {
      return size;
    }

    /** Remove the first occurrence of the element o from this list.
     *  Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e) {
      if (indexOf(e) >= 0) {
        remove(indexOf(e));
        return true;
      }
      else
        return false;
    }
  }

  public interface MyList<E> {
    /** Add a new element at the end of this list */
    public void add(E e);

    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Clear the list */
    public void clear();

    /** Return true if this list contains the element */
    public boolean contains(E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(E e);

    /** Return true if this list contains no elements */
    public boolean isEmpty();

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the first occurrence of the element o from this list.
     *  Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public boolean remove(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and returns the new set. */
    public Object set(int index, E e);

    /** Return the number of elements in this list */
    public int size();
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new Exercise25_16());
    frame.setTitle("Exercise25_16");
    frame.setSize(600, 150);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}