public class Exercise20_8 {
  public static void main(String[] args) {
    reverseDisplay(123456);
  }

  public static void reverseDisplay(int value) {
    if (value != 0) {
      System.out.print(value % 10);
      value = value / 10;
      reverseDisplay(value);
    }
  }
}
