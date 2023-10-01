import java.io.*;
import java.util.Scanner;

public class Exercise19_16 {
  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a filename: ");
    String filename = input.nextLine();
    
    BufferedInputStream fileInput = new BufferedInputStream(
      new FileInputStream(new File(filename)));
    
    int[] counts = new int[128];
    int r;
    while ((r = fileInput.read()) != -1 ) {
      counts[(byte)r]++;
    }
    
    System.out.printf("%15s%15s\n", "ASCII Code", "Counts");
    for (int i = 0; i < counts.length; i++)
      if (counts[i] != 0)
        System.out.printf("%15d%15d\n", i, counts[i]);
  }
}