import java.util.*;

public class Exercise11_6 {
  public static void main(String[] args) {
    ArrayList list = new ArrayList();
    list.add(new Loan());
    list.add(new Date());
    list.add(new javax.swing.JFrame());

    for (int i = 0; i < list.size(); i++)
      System.out.println(list.get(i));
  }
}
