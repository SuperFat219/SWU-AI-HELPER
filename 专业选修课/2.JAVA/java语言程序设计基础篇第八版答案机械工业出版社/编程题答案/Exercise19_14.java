import java.util.Scanner;
import java.io.*;

public class Exercise19_14 { 
  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(System.in);
    
    System.out.print("Enter a file to encrypt: ");
    BufferedInputStream in = new BufferedInputStream(
      new FileInputStream(new File(input.next())));

    System.out.print("Enter the output file: ");
    BufferedOutputStream output = new BufferedOutputStream(
      new FileOutputStream(new File(input.next())));

    int value;
    while ((value = in.read()) != -1) {
      output.write(value + 5);
    }
    
    input.close();
    output.close();
  }
}
