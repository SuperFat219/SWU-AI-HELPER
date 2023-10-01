import java.io.*;

public class CheckNumberOfPrimes {
  public static void main(String[] args) throws Exception {
    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
      countNumberOfPrimes(args[i]);
    }
  }

  public static void countNumberOfPrimes(String filename) throws Exception {
    int count = 0; // Count the number of prime numbers

    DataInputStream input =
      new DataInputStream(new BufferedInputStream(
      new FileInputStream(filename)));

    long[] limits = {10, 100, 1000, 10000, 100000, 1000000,
      10000000, 100000000, 1000000000, 10000000000L,
      100000000000L, 1000000000000L};
    int k = 0;

    while (input.available() > 0) {
      if (input.readLong() > limits[k]) {
        System.out.println("Number of prime number <= " +
          limits[k] + " is " + count);
        k++;
      }
      count++;
    }

    input.close();
  }
}
