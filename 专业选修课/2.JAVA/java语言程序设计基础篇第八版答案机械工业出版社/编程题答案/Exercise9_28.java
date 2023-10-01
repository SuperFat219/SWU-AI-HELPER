import java.util.Scanner;

public class Exercise9_28 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter two strings
    System.out.print("Enter the first string: ");
    String s1 = input.next();
    System.out.print("Enter the second string: ");
    String s2 = input.next();

    String s3 = prefix(s1, s2);

    if (s3 != null) {
      System.out.println("The common prefix is " + s3);
    }
    else {
      System.out.println("No common prefix");
    }
  }

  public static String prefix(String s1, String s2) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < s1.length(); i++)
		{
			if (s1.charAt(i) == s2.charAt(i))
				result.append(s1.charAt(i));
			else
				break;
		}

	  if (result.length() == 0)
			return null;
		else
		  return result.toString();
  }
}
