import java.io.*;

public class Exercise19_6 {
  public static void main(String[] args) throws IOException {
    ObjectOutputStream output = new ObjectOutputStream(
      new FileOutputStream("Exercise19_6.dat"));

    output.writeObject(new Loan(2.5, 5, 1000));
    output.writeObject(new Loan(3.5, 5, 2000));
    output.writeObject(new Loan(4.5, 5, 3000));
    output.writeObject(new Loan(5.5, 5, 4000));
    output.writeObject(new Loan(6.5, 5, 5000));

    output.close();
  }
}
