import java.io.*;
import java.util.*;

class Exercise9_34 {
  public static void main(String[] args) throws Exception {
    // Check command line parameter usage
    if (args.length != 3) {
      System.out.println(
        "Usage: java Exercise9_34 dir oldStr newStr");
      System.exit(0);
    } 

    // Check if source file exists
    File currentDir = new File(args[0]);
    System.out.println(currentDir.getName());
    
    if (!currentDir.exists()) {
      System.out.println("Current directory " + args[0] + " deos not exist");
      System.exit(0);
    }
    
    if (currentDir.isFile()) {
      replaceInAFile(currentDir, args[1], args[2]); // currentDir is a file
    }
    else {
      File[] files = currentDir.listFiles();
      for (int i = 0; i < files.length; i++)
    	if (files[i].isFile())
          replaceInAFile(files[i], args[1], args[2]);
    }
  }
  
  private static void replaceInAFile(File sourceFile, String oldStr, String newStr) {
	try {
      // Read text from the file and save it in a string builder
      Scanner input = new Scanner(sourceFile);
      StringBuilder sb = new StringBuilder();

      while (input.hasNext()) {
        String s1 = input.nextLine();
        String s2 = s1.replaceAll(oldStr, newStr);
        sb.append("\r\n" + s2);
      }

      input.close();

      // Write back to the file
      PrintWriter output = new PrintWriter(sourceFile);
      output.printf("%s\r\n", sb.toString());
      output.close(); 
	}
	catch (Exception ex) {
	  ex.printStackTrace();
	}
  }
}
