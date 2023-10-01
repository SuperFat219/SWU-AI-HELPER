// This application program prints Welcome to Java!
public class Temp { 
  public static void main(String[] args) { 
    try {
      int[] list = new int[20000000];
    }
    catch (Error ex) {
      ex.printStackTrace();
      System.out.println("Here");
    }
    
    System.out.println("GO");
    
    javax.swing.JOptionPane.showMessageDialog(null, "Wait");
  }
}
