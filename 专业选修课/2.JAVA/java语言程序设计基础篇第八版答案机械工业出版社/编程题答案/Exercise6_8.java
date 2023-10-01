// Exercise6_8.java:
public class Exercise6_8 {
  // Main method
  public static void main(String[] args) {
    int[] list1 = {1, 2, 3, 4, 5, 6};
    double[] list2 = {6.0, 4.4, 1.9, 2.9, 3.4, 3.5};

    System.out.println(average(list1));
    System.out.println(average(list2));
  }

  public static int average(int[] array) {
    int sum = 0;
    for (int i = 0; i < array.length; i++)
      sum += array[i];
    return sum / array.length;
  }

  public static double average(double[] array) {
    double sum = 0;
    for (int i = 0; i < array.length; i++)
      sum += array[i];
    return sum / array.length;
  }
}
