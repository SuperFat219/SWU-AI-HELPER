public class Exercise20_16 {
  public static void main(String[] args) {
    System.out.println(count(new char[]{'W', 'e', 'l', 'c', 'o', 'm', 'E'}));
  }

  public static int count(char[] chars) {
    return count(chars, chars.length - 1);
  }

  public static int count(char[] chars, int high) {
    if (high >= 0) {
      return count(chars, high - 1) +
        (Character.isUpperCase(chars[high]) ? 1 : 0);
    }
    else
      return 0;
  }
}
