import java.io.*;

public class Exercise19_8 {
  public static void main(String[] args) throws IOException {
    RandomAccessFile raf = new RandomAccessFile("Exercise19_8.dat", "rw");

    int count = 0;

    if (raf.length() > 0)
      count = raf.readInt();

    raf.seek(0);
    raf.writeInt(++count);
    System.out.println("Current count is " + count);
    raf.close();
  }
}
