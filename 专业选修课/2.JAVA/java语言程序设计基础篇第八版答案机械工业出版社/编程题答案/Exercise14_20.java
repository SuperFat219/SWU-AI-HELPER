import java.util.StringTokenizer;

public class Exercise14_20 {
  public static void main(String[] args) {
    Rational result = new Rational(0, 1);

    if (args.length != 3) {
      System.out.println(
        "Usage: java Exercise14_20 operand1 operator operand2");
      System.exit(0);
    }

    switch (args[1].charAt(0)) {
      case '+': result = getRational(args[0]).add(getRational(args[2]));
      break;
      case '-': result = getRational(args[0]).subtract(getRational(args[2]));
      break;
      case '*': result = getRational(args[0]).multiply(getRational(args[2]));
      break;
      case '/': result = getRational(args[0]).divide(getRational(args[2]));
    }

    System.out.println(args[0] + " " + args[1] + " " + args[2] + " = " + result);
  }

  static Rational getRational(String s) {
    StringTokenizer st = new StringTokenizer(s, "/");
    int numer = new Integer(st.nextToken()).intValue();
    int denom = new Integer(st.nextToken()).intValue();
    return new Rational(numer, denom);
  }
}
