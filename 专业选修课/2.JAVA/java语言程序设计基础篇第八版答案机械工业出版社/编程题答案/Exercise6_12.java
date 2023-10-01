public class Exercise6_12 {
  public static void main(String[] args) {
     int[] myList = {1, 2, 3, 4, 5, 6, 7, 8};
     reverse(myList);

     for (int i = 0; i < myList.length; i++)
       System.out.print(myList[i] + " ");
  }

  public static int[] reverse(int[] list) {
    for (int i = 0, j = list.length - 1; i < list.length / 2; i++, j--) {
      int temp = list[i];
      list[i] = list[j];
      list[j] = temp;
    }

    return list;
  }
}
