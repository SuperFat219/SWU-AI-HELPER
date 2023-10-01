public class Exercise24_8 {
  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    heapSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }
  }

  public static void heapSort(Object[] list) {
    Heap heap = new Heap(); // Create a Heap

    // Add elements to the heap
    for (int i = 0; i < list.length; i++) {
      heap.add(list[i]);
    }

    // Remove elements from the heap
    for (int i = list.length - 1; i >= 0; i--) {
      list[i] = heap.remove();
    }
  }

  static class Heap {
    private java.util.ArrayList list = new java.util.ArrayList();

    /** Create a default heap */
    public Heap() {
    }

    /** Create a heap from an array of objects */
    public Heap(Object[] objects) {
      for (int i = 0; i < objects.length; i++) {
        add(objects[i]);
      }
    }

    /** Add a new object into the heap */
    public void add(Object newObject) {
      list.add(newObject); // Append to the heap
      int currentIndex = list.size() - 1; // The index of the last node

      while (currentIndex > 0) {
        int parentIndex = (currentIndex - 1) / 2;
        // Swap if the current object is greater than its parent
        if (((Comparable)(list.get(currentIndex))).compareTo(
          list.get(parentIndex)) > 0) {
          Object temp = list.get(currentIndex);
          list.set(currentIndex, list.get(parentIndex));
          list.set(parentIndex, temp);
        }
        else {
          break; // the tree is a heap now
        }

        currentIndex = parentIndex;
      }
    }

    /** Remove the root from the heap */
    public Object remove() {
      if (list.size() == 0) {
        return null;
      }

      Object removedObject = list.get(0);
      list.set(0, list.get(list.size() - 1));
      list.remove(list.size() - 1);

      int currentIndex = 0;
      while (currentIndex < list.size()) {
        int leftChildIndex = 2 * currentIndex + 1;
        int rightChildIndex = 2 * currentIndex + 2;

        // Find the maximum between two children
        if (leftChildIndex >= list.size()) {
          break; // The tree is a heap
        }
        int maxIndex = leftChildIndex;
        if (rightChildIndex < list.size()) {
          if (((Comparable)(list.get(maxIndex))).compareTo(
            list.get(rightChildIndex)) < 0) {
            maxIndex = rightChildIndex;
          }
        }

        // Swap if the current node is less than the maximum
        if (((Comparable)(list.get(currentIndex))).compareTo(
          list.get(maxIndex)) < 0) {
          Object temp = list.get(maxIndex);
          list.set(maxIndex, list.get(currentIndex));
          list.set(currentIndex, temp);
          currentIndex = maxIndex;
        }
        else {
          break; // The tree is a heap
        }
      }

      return removedObject;
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
      return list.size();
    }
  }

}
