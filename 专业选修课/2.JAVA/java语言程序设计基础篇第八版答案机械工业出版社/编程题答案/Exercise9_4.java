public class Exercise9_4 {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter a string: ");
    String s = input.nextLine();

    // Prompt the user to enter a character
    System.out.print("Enter a character: ");
    char ch = input.nextLine().charAt(0);

    int count = count(s, ch);
    System.out.println(count);
  }

  public static int count(String str, char ch) {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ch) {
        count++;
      }
    }
    return count;
  }
}
