public class Exercise20_10 {
  public static void main(String[] args) {
    System.out.println(count("Welcome", 'e'));
  }

  public static int count(String str, char a) {
    int result = 0;
    if (str.length() > 0)
      result = count(str.substring(1), a) +
        ((str.charAt(0) == a) ? 1 : 0);

    return result;
  }
}
