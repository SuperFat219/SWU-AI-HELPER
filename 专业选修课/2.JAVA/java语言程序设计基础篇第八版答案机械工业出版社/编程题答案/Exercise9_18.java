import java.util.*;
import java.io.*;

public class Exercise9_18 {
  public static void main(String[] args) throws Exception {
    Scanner input = new Scanner(new File("Exercise8_18.txt"));
    double sum = 0;

    while (input.hasNext()) {
      sum += input.nextDouble();
    }

    System.out.println("Total is " + sum);
  }
}
