import java.util.*;

public class Exercise22_8 {
  public static void main(String[] args) {
    // Text in a string
    String text = "Have a good day. Have a good class. " +
      "Have a good visit. Have fun!";

    // Create a hash map to hold words and key and count as value
    HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

    StringTokenizer st = new StringTokenizer(text, " .!?");
    while (st.hasMoreTokens()) {
      String key = st.nextToken();

      if (hashMap.get(key) != null) {
        int value = ((Integer)hashMap.get(key)).intValue();
        value++;
        hashMap.put(key, new Integer(value));
      }
      else {
        hashMap.put(key, new Integer(1));
      }
    }

    // Create a tree map from the hash map
    TreeMap<String, Integer> treeMap =
      new TreeMap<String, Integer>(hashMap);

    // Get an entry set for the tree map
    Set entrySet = treeMap.entrySet();

    // Get an iterator for the entry set
    Iterator iterator = entrySet.iterator();

    ArrayList<WordOccurrence> list = new ArrayList<WordOccurrence>();

    while (iterator.hasNext()) {
      StringTokenizer st1 =
        new StringTokenizer(iterator.next().toString(), "=");
      list.add(new WordOccurrence(st1.nextToken(),
        Integer.parseInt(st1.nextToken())));
    }

    Collections.sort(list);
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
  }
}

class WordOccurrence implements Comparable {
  String word;
  int count;

  public WordOccurrence(String word, int count) {
    this.word = word;
    this.count = count;
  }

  public int compareTo(Object o) {
    return count - ((WordOccurrence)o).count;
  }

  public boolean equals(Object o) {
    return word.equals(((WordOccurrence)o).word);
  }

  public String toString() {
    return word + " " + count;
  }
}
