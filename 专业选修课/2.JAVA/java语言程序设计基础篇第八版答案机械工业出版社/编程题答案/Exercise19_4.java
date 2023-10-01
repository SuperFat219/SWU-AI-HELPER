import java.io.*;

public class Exercise19_4 {
  public static void main(String[] args) throws IOException {
    BufferedReader input = new BufferedReader(new FileReader(args[0]));
    DataOutputStream output = new DataOutputStream(new FileOutputStream(args[1]));

    String line;
    while ((line = input.readLine()) != null)
      output.writeUTF(line);

    input.close();
    output.close();

    InputStream input1 = new FileInputStream(args[0]);
    System.out.println(args[0] + "'s size is " + input1.available() + " bytes");
    input1 = new FileInputStream(args[1]);
    System.out.println(args[1] + "'s size is " + input1.available() + " bytes");

    input1.close();
  }
}
