public class Exercise4_14 {
  public static void main(String[] args) {
    int count = 1;
    for (int i = '!'; i < '~'; i++) {
      System.out.print((count++ % 10 != 0) ? (char)i + " " :
        (char)i + "\n");
    }
  }
}
