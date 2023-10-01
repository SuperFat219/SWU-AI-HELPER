// Bubble sort
public class Exercise6_18 {
  public static void main (String[] args) {
    double[] myList = {5.0, 4.4, 1.9, 2.9, 3.4, 2.9, 3.5};
    System.out.println("My list before sort is: ");

    //prints the original list
    printList(myList);
    bubbleSort(myList);

    //prints the sorted list
    System.out.println("My list after sort is: ");
    printList(myList);
  }

  static void printList(double[] list) {
    for (int i = 0; i < list.length; i++)
      System.out.println(list[i]);
  }

  static void bubbleSort(double[] list) {
    boolean changed = true;
    do {
      changed = false;
      for (int j = 0; j < list.length - 1; j++)
        if (list[j] > list[j+1]) {
          //swap list[j] wiht list[j+1]
          double temp = list[j];
          list[j] = list[j + 1];
          list[j + 1] = temp;
          changed = true;
        }
    } while (changed);
  }
}
