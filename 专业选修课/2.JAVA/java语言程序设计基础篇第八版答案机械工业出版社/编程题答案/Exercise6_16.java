public class Exercise6_16 {
  public static void main(String[] args) {
    int[] list = new int[100000];
    for (int i = 0; i < list.length; i++) {
      list[i] = (int)(Math.random() * 1000000);
    }

    int key = (int)(Math.random() * 1000000);
    long startTime = System.currentTimeMillis();
    System.out.println(LinearSearch.linearSearch(list, key));
    long endTime = System.currentTimeMillis();
    long executionTime = endTime - startTime;
    System.out.println("Execution time for linear search is " +
                       executionTime);

    java.util.Arrays.sort(list);
    startTime = System.currentTimeMillis();
    System.out.println(BinarySearch.binarySearch(list, key));
    endTime = System.currentTimeMillis();
    executionTime = endTime - startTime;
    System.out.println("Execution time for binary search is " +
                       executionTime);

  }

}
