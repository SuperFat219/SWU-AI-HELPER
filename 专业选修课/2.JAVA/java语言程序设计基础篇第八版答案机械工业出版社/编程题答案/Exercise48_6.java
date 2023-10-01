import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Exercise48_6 extends JApplet {
  private MyHashMap<Integer, Integer> map 
    = new MyHashMap<Integer, Integer>();
  private HashView view = new HashView();
  private JButton jbtInsert = new JButton("Insert");
  private JButton jbtDelete = new JButton("Delete");
  private JButton jbtRemoveAll = new JButton("Remove All");
  private JTextField jtfNumber = new JTextField(2);
  
  public Exercise48_6() {    
    JPanel panel = new JPanel();
    panel.add(new JLabel("Enter a value: "));
    panel.add(jtfNumber);
    panel.add(jbtInsert);
    panel.add(jbtDelete);
    panel.add(jbtRemoveAll);
    
    add(new JScrollPane(view));  
    add(panel, BorderLayout.SOUTH);

    jbtInsert.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        map.put(Integer.parseInt(jtfNumber.getText()), 1);
        view.repaint();
      }
    });
    
    jbtDelete.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        map.remove(Integer.parseInt(jtfNumber.getText()));
        view.repaint();
      }
    });    
    
    jbtRemoveAll.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        map.clear();
        view.repaint();
      }
    });
  }
  
  public class HashView extends JPanel {
    private int startingX = 20;
    private int startingY = 20;
    private int boxWidth = 40;
    private int boxHeight = 20;
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      g.setFont(new Font("Courier", Font.PLAIN, 14));
      
      g.drawString("Table size = " + map.capacity + 
          ". Number of keys = " + map.size,
          startingX, startingY);
      g.drawString("Load factor = " + 1.0 * map.size / map.capacity 
          + ". Load factor threshold = " + map.loadFactorThreshold + ".",
          startingX, startingY + 20);

      int x = startingX;
      int y = startingY + 55;        

      for (int i = 0; i < map.capacity; i++) {
        g.drawString("[" + i + "]", x, y);
        g.drawRect(startingX + 35, y - 15, boxWidth, boxHeight);
        y += 20;        
      }
      
      x += 50;
      y = startingY + 55;        
      for (int i = 0; i < map.capacity; i++) {
        if (map.table[i] != null && map.table[i].key != null)
          g.drawString(map.table[i].key + "", x, y);
        else if (map.table[i] != null && map.table[i].key == null)
          g.drawString("X", x, y);

        y += 20;        
      }
    }
  }
  
  public static void drawArrowLine(int x1, int y1, 
      int x2, int y2, Graphics g) {
    g.setColor(Color.red);
    g.drawLine(x1, y1, x2, y2);
    
    // find slope of this line
    double slope = ((((double) y1) - (double) y2))
        / (((double) x1) - (((double) x2)));

    double arctan = Math.atan(slope);

    // This will flip the arrow 45 off of a
    // perpendicular line at pt x2
    double set45 = 1.57 / 2;
    
    // arrows should always point towards i, not i+1
    if (x1 < x2) {
      // add 90 degrees to arrow lines
      set45 = -1.57 * 1.5;
    }

    // set length of arrows
    int arrlen = 10;

    // draw arrows on line
    g.drawLine(x2, y2, (int) ((x2 + (Math.cos(arctan + set45) * arrlen))),
      (int)(((y2)) + (Math.sin(arctan + set45) * arrlen)));

    g.drawLine(x2, y2, (int) ((x2 + (Math.cos(arctan - set45) * arrlen))),
      (int)(((y2)) + (Math.sin(arctan - set45) * arrlen)));
  }
  
  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(new Exercise48_6());
    frame.setTitle("Exercise48_6");
    frame.setSize(450, 530);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  
  static class MyHashMap<K, V> implements MyMap<K, V> {
    // Define the default hash table size. 
    private static int DEFAULT_INITIAL_CAPACITY = 4;
        
    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30; 
    
    // Current hash table capacity. 
    private int capacity;
    
    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f; 

    // Specify a load factor used in the hash table
    private float loadFactorThreshold; 
       
    // The number of entries in the map
    private int size = 0; 
    
    // Hash table is an array with each cell that is a linked list
    MyMap.Entry<K,V>[] table;

    /** Construct a map with the default capacity and load factor */
    public MyHashMap() {  
      this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a map with the specified initial capacity and 
     * default load factor */
    public MyHashMap(int initialCapacity) { 
      this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);    
    }
    
    /** Construct a map with the specified initial capacity 
     * and load factor */
    public MyHashMap(int initialCapacity, float loadFactorThreshold) { 
      this.capacity = initialCapacity;     
      this.loadFactorThreshold = loadFactorThreshold;    
      table = new MyMap.Entry[capacity];
    }
    
    /** Remove all of the entries from this map */ 
    public void clear() {
      size = 0;
      removeEntries();
    }

    /** Return true if the specified key is in the map */
    public boolean containsKey(K key) {
      int i = hash(key.hashCode());
      while (table[i] != null) 
        if (table[i].getKey().equals(key)) 
          return true;
      
      return false;
    }
    
    /** Return true if this map contains the specified value */ 
    public boolean containsValue(V value) {
      for (int i = 0; i < table.length; i++)
        if (table[i] != null && table[i].value.equals(value)) 
          return true;
      
      return false;
    }
    
    /** Return a set of entries in the map */
    public java.util.Set<MyMap.Entry<K,V>> entrySet() {
      java.util.Set<MyMap.Entry<K, V>> set = 
        new java.util.HashSet<MyMap.Entry<K, V>>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null) 
          set.add(table[i]); 
      
      return set;
    }

    /** Return the first value that matches the specified key */
    public V get(K key) {
      // Perform linear probing
      int i = hash(key.hashCode());
      
      while (table[i] != null) {
        if (table[i].key.equals(key)) 
          return table[i].value;
        i = (i + 1) % table.length;
      }
      
      return null;
    }
    
    /** Return all values for the specified key in this map */
    public java.util.Set<V> getAll(K key) {
      java.util.Set<V> set = new java.util.HashSet<V>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null && table[i].key.equals(key)) 
          set.add(table[i].value); 
      
      return set;
    }

    /** Return true if this map contains no entries */
    public boolean isEmpty() {
      return size == 0;
    }  
    
    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet() {
      java.util.Set<K> set = new java.util.HashSet<K>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null) 
          set.add(table[i].key); 
      
      return set;
    }
        
    /** Add an entry (key, value) into the map */
    public V put(K key, V value) {
      if (size >= capacity * loadFactorThreshold) {
        if (capacity == MAXIMUM_CAPACITY)
          throw new RuntimeException("Exceeding maximum capacity");
        
        rehash();
      }
      
      int i = hash(key.hashCode());
      
      while (table[i] != null && table[i].key != null) 
        i = (i + 1) % table.length;
      
      // Add an entry (key, value) to the table
      table[i] = new MyMap.Entry<K, V>(key, value);

      size++; // Increase size
      
      return value;  
    } 
   
    /** Remove the element for the specified key */
    public void remove(K key) {
      int i = hash(key.hashCode());
      
      while (table[i] != null && (table[i].key == null || !table[i].key.equals(key)))
        i = (i + 1) % table.length;

      if (table[i] != null && table[i].key.equals(key)) {
        // A special marker Entry(null, null) is placed for the deleted entry
        table[i] = new MyMap.Entry<K, V>(null, null);
        size--;
      }
    }
    
    /** Return the number of mappings in this map */
    public int size() {
      return size;
    }
    
    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values() {
      java.util.Set<V> set = new java.util.HashSet<V>();
      
      for (int i = 0; i < capacity; i++) 
        if (table[i] != null) 
          set.add(table[i].value); 
      
      return set;
    }
    
    /** Hash function */
    private int hash(int hashCode) {
      return supplementalHash(hashCode) & (capacity - 1);
    }
    
    /** Ensure the hashing is evenly distributed */
    private static int supplementalHash(int h) {
      h ^= (h >>> 20) ^ (h >>> 12);
      return h ^ (h >>> 7) ^ (h >>> 4);
    }
    
    /** Remove all entries from each bucket */
    private void removeEntries() {
      for (int i = 0; i < capacity; i++) 
        table[i] = null;
    }
    
    /** Rehash the map */
    private void rehash() {
      java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
      capacity <<= 1; // Double capacity    
      table = new Entry[capacity]; // Create a new hash table
      
      size = 0; // Clear size
      
      for (Entry<K, V> entry: set) {
        put(entry.getKey(), entry.getValue()); // Store to new table
      }
    }

    /** Return a string representation for this map */
    public String toString() {
      StringBuilder builder = new StringBuilder("[");
      
      for (int i = 0; i < capacity; i++) {
        if (table[i] != null && table[i].key != null) 
          builder.append(table[i].toString());
      }
      
      return builder.append("]").toString();
    }
  }

  interface MyMap<K, V> {
    /** Remove all of the entries from this map */ 
    public void clear();
    
    /** Return true if the specified key is in the map */
    public boolean containsKey(K key);
    
    /** Return true if this map contains the specified value */ 
    public boolean containsValue(V value);

    /** Return a set of entries in the map */
    public java.util.Set<Entry<K, V>> entrySet();

    /** Return the first value that matches the specified key */
    public V get(K key);
    
    /** Return all values for the specified key in this map */
    public java.util.Set<V> getAll(K key);
    
    /** Return true if this map contains no entries */
    public boolean isEmpty();

    /** Return a set consisting of the keys in this map */
    public java.util.Set<K> keySet();
    
    /** Add an entry (key, value) into the map */
    public V put(K key, V value);

    /** Remove the entries for the specified key */
    public void remove(K key);

    /** Return the number of mappings in this map */
    public int size();

    /** Return a set consisting of the values in this map */
    public java.util.Set<V> values();
    
    /** Define inner class for Entry */
    public static class Entry<K, V> {
      K key;
      V value;
      
      public Entry(K key, V value) {
        this.key = key;
        this.value = value;
      }
      
      public K getKey() {
        return key;
      }
      
      public V getValue() {
        return value;
      }
      
      public String toString() {
        return "[" + key + ", " + value + "]";
      }
    }
  }    
}
