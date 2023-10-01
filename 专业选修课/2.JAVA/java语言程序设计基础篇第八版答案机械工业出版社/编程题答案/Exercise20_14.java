public class Exercise20_14 {
  public static void main(String[] args) {
    System.out.println(countUppercase("Welcome to Java"));
  }

  public static int countUppercase(String str) {
    return countUppercase(str, str.length() - 1);
  }

  public static int countUppercase(String str, int high) {
    if (high == 0)
      return Character.isUpperCase(str.charAt(0)) ? 1 : 0;
    else
      return countUppercase(str, high - 1) +
        (Character.isUpperCase(str.charAt(high)) ? 1 : 0);
  }
}
