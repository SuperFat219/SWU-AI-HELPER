
public class Test1 {
  public static void main(String[] args) {
    double pi = 0;

    for (int i = 1; i <= 100000; i += 2) {
      pi += 1.0 / (2 * i - 1) - 1.0 / (2 * i + 1);

      if (i == 10001 || i == 20000 || i == 30000 || i == 40000 ||
          i == 50000 || i == 60000 || i == 70000 || i == 80000 ||
          i == 90000 || i == 100000)
      System.out.println("The PI is " + 4 * pi + " for i = " + i);
    }
  }
}
