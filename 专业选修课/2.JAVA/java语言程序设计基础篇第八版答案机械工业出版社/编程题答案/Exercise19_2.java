import java.io.*;

public class Exercise19_2 {
  public static void main(String[] args) throws IOException {
    DataOutputStream output =
      new DataOutputStream(new FileOutputStream("Exercise19_2.dat", true));

    for (int i = 0; i < 100; i++)
      output.writeInt((int)(Math.random() * 100000));

    output.close();
    
    System.out.println("Done");
  }
}
