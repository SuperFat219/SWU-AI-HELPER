public class RecursiveInsertionSort {
  public static void sort(double[] list) {
    sort(list, list.length - 1);
  }

  public static void sort(double[] list, int high) {
    if (high > 0) {
      // Sort the list[0 ... high – 1]
      sort(list, high - 1);

      // Insert list[high] into list
      double lastElement = list[high];
      int k;
      for (k = high - 1; k >= 0 && list[k] > lastElement; k--) {
        list[k + 1] = list[k];
      }
      list[k + 1] = lastElement;

    }
  }

  public static void main(String[] args) {
    double[] list = {7, 1, 2, 8, 3, 4, 5, 6};
    sort(list);

    for (int i = 0; i < list.length; i++)
      System.out.print(list[i] + " ");
  }
}
