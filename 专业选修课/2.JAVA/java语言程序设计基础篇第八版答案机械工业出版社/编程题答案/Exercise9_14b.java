import java.util.StringTokenizer;

public class Exercise9_14b {
  public static void main(String[] args) {
    int sum = 0;

    StringTokenizer st = new StringTokenizer(args[0]);

    while (st.hasMoreTokens()) {
      sum += Integer.parseInt(st.nextToken());
    }

    System.out.println("The total is " + sum);
  }
}
