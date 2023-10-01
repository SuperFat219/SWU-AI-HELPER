// Exercise20_2.java: Iterative solution for the Fibonacci number
public class Exercise20_2 {
  public static void main(String[] args) {
    System.out.println("The fibonacci(10) is " + fib(10));
  }

  public static long fib(int n) {
    int f0 = 0, f1 = 1, currentFib;

    if (n == 0) return 0;
    if (n == 1) return 1;

    for (int i = 1; i <= n; i++) {
      currentFib = f0+f1;
      f0 = f1;
      f1 = currentFib;
    }

    return f1;
  }
}
