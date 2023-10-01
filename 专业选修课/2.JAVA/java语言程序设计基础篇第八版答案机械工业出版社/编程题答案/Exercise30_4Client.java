// Exercise30_4Client.java: Web visit count client
/** Written in JDK1.02 so it can run on any Java VM 1.02, 1.1 and 1.2.
   However, you will get a compilation warning on JDK 1.1 or above,
   because it contains deprecated methods.
*/
import java.io.*;
import java.net.*;
import java.awt.*;
import java.applet.Applet;

public class Exercise30_4Client extends Applet {
  private Label lb1 = new Label("You are visitor number ");
  private Label lb2 = new Label("    ");

  public void init() {
    setLayout(new FlowLayout());
    add(lb1);
    lb1.setBackground(Color.yellow);
    add(lb2);
    lb2.setBackground(Color.cyan);

    try {
      // Establish connection with the server
      // Socket connectToServer = new Socket("liangy.ipfw.edu", 8000);
      Socket connectToServer = new Socket("localhost", 8000);

      BufferedReader isFromServer =
        new BufferedReader(
                new InputStreamReader(connectToServer.getInputStream()));

      String count = isFromServer.readLine();

      lb2.setText(count);
    }
    catch (IOException ioe) {System.err.println(ioe);}
  }
}
