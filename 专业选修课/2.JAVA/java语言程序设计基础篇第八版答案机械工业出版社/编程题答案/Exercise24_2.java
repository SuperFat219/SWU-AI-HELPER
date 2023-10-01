import java.util.Comparator;

public class Exercise24_2 {
  /** The method for sorting the numbers */
  public static <E extends Comparable<E>> void mergeSort(E[] list) {
    if (list.length > 1) {
      // Merge sort the first half
      E[] firstHalf = (E[])new Comparable[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      E[] secondHalf = (E[])new Comparable[secondHalfLength];
      System.arraycopy(list, list.length / 2,
                       secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf
      E[] temp = merge(firstHalf, secondHalf);
      System.arraycopy(temp, 0, list, 0, temp.length);
    }
  }

  private static<E extends Comparable<E>> E[]
    merge(E[] list1, E[] list2) {
    E[] temp = (E[])new Comparable[list1.length + list2.length];

    int current1 = 0; // Index in list1
    int current2 = 0; // Index in list2
    int current3 = 0; // Index in temp

    while (current1 < list1.length && current2 < list2.length) {
      if (list1[current1].compareTo(list2[current2]) < 0) {
        temp[current3++] = list1[current1++];
      }
      else {
        temp[current3++] = list2[current2++];
      }
    }

    while (current1 < list1.length) {
      temp[current3++] = list1[current1++];
    }

    while (current2 < list2.length) {
      temp[current3++] = list2[current2++];
    }

    return temp;
  }

  public static void main(String[] args) {
    Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
    mergeSort(list);
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }

    System.out.println();
    Circle[] list1 = {new Circle(2), new Circle(3), new Circle(2),
                     new Circle(5), new Circle(6), new Circle(1), new Circle(2),
                     new Circle(3), new Circle(14), new Circle(12)};
    mergeSort(list1, new GeometricObjectComparator());
    for (int i = 0; i < list1.length; i++) {
      System.out.print(list1[i] + " ");
    }

  }

  public static <E> void mergeSort(E[] list,
	    Comparator<? super E> comparator) {
    if (list.length > 1) {
      // Merge sort the first half
      E[] firstHalf = (E[])new Object[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf, comparator);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      E[] secondHalf = (E[])new Object[secondHalfLength];
      System.arraycopy(list, list.length / 2,
                       secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf, comparator);

      // Merge firstHalf with secondHalf
      E[] temp = merge1(firstHalf, secondHalf, comparator);
      System.arraycopy(temp, 0, list, 0, temp.length);
    }
  }

  private static<E> E[]
    merge1(E[] list1, E[] list2, Comparator<? super E> comparator) {
    E[] temp = (E[])new Object[list1.length + list2.length];

    int current1 = 0; // Index in list1
    int current2 = 0; // Index in list2
    int current3 = 0; // Index in temp

    while (current1 < list1.length && current2 < list2.length) {
      if (comparator.compare(list1[current1], list2[current2]) < 0) {
        temp[current3++] = list1[current1++];
      }
      else {
        temp[current3++] = list2[current2++];
      }
    }

    while (current1 < list1.length) {
      temp[current3++] = list1[current1++];
    }

    while (current2 < list2.length) {
      temp[current3++] = list2[current2++];
    }

    return temp;
  }
}
