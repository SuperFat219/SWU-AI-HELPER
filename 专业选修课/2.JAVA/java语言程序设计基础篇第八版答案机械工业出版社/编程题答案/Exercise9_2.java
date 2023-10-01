public class Exercise9_2 {
  public static void main(String[] args) {
    // Prompt the user to enter a string
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print("Enter the first string: ");
    String first = input.nextLine();

    System.out.print("Enter the second string: ");
    String second = input.nextLine();

    if (isSubstring(first, second)) {
      System.out.println(first + " is a substring of " + second);
    }
    else {
      System.out.println(first + " is not a substring of " + second);
    }
  }

  /**Check if the first string is a substring of the second string*/
  public static boolean isSubstring(String first, String second) {
    int remainingLength = second.length();
    int startingIndex = 0;

    // Note toWhile is a label. You can use break with a label
    // attached.
    toWhile: while (first.length() <= remainingLength) {
      // What is wrong if the following line is used
      // for (int i=startingIndex; i<=first.length(); i++)
      for (int i = 0; i < first.length(); i++) {
        if (first.charAt(i) != second.charAt(startingIndex+i)) {
          startingIndex++;
          remainingLength--;
          continue toWhile;
        }
      }

      return true;
    }

    return false;
  }
}

