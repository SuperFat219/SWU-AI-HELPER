public class Exercise5_14 {
  public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter i: ");
    int i = input.nextInt();
    System.out.println(m(i));

//    System.out.println("i\t\tm(i)");
//    for (int i = 2; i <= 10000; i++)
//      System.out.println(i + "\t\t" + m(i));
  }

  // New solution after 6E, 2/04/07
  public static double m(int n) {
    double pi = 0;
    double term;

    for (int i = 1; i <= n; i += 2) {
      term = 4.0 * (1.0 / (2 * i - 1) - 1.0 / (2 * i + 1));
      pi += term;
    }

    return pi;
  }

  // old solution prior to 6E
//  public static double m(int n) {
//    double pi = 0;
//    double term;
//    int sign = 1;
//
//    for (int i = 1; i <= n; i++) {
//      term = sign * 4.0 / (2 * i - 1);
//      pi += term;
//      sign = -1 * sign;
//    }
//
//    return pi;
//  }
}
