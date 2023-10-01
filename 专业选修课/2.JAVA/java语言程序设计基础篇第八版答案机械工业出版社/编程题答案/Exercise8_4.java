import java.util.Random;

public class Exercise8_4 {
  public static void main(String[] args) {
    Random random = new Random(1000);
    
    for (int i = 0; i < 50; i++) 
      System.out.print(random.nextInt(100) + " ");
  }
}
