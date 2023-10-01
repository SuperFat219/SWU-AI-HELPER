import java.util.*;
import java.io.*;

public class Exercise22_3UsingBufferReader {
  public static void main(String[] args) {
    // Check usage
    if (args.length != 1) {
      System.out.println("Usage: java Exercise22_3 file.java");
      System.exit(0);
    }

    // Array of all Java keywords + true + null
    String[] keywordString = {"abstract", "finally", "public",
      "boolean", "float", "return", "break", "for", "short", "byte",
      "goto", "static", "case", "if", "super", "catch", "implements",
      "switch", "char", "import", "synchronized", "class",
      "instanceof", "this", "const", "int", "throw", "continue",
      "interface", "throws", "default", "long", "transient", "do",
      "native", "try", "double", "new", "void", "else", "package",
      "volatile", "extends", "private", "while", "final",
      "protected", "true", "null"};

    Set keywordSet = new HashSet(Arrays.asList(keywordString));
    int count = 0;
    
    BufferedReader in = null;

    try {
      in = new BufferedReader(new FileReader(args[0]));

      String s = null;
      while ( (s=in.readLine()) != null) {
        // Extract tokens
        StringTokenizer st = new StringTokenizer(s);

        while (st.hasMoreTokens()) {
          String token = st.nextToken();
          if (keywordSet.contains(token))
            count++;
        }
      }

      System.out.println("The number of keywords in the program is "
        + count);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

