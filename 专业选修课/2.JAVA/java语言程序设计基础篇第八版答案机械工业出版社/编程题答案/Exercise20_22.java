public class Exercise20_22 {
  public static void main(String[] args) {
    System.out.println(decimalToHex(10));
    System.out.println(decimalToHex(12));
    System.out.println(decimalToHex(14));
    System.out.println(decimalToHex(16));
    System.out.println(decimalToHex(18));
  }  

  public static String decimalToHex(int value) {
    if (value == 0)
      return "";
    else {
      String temp;
      if (value % 16 == 10)
        temp = "A";
      else if (value % 16 == 11)
        temp = "B";
      else if (value % 16 == 12)
        temp = "C";
      else if (value % 16 == 13)
        temp = "D";
      else if (value % 16 == 14)
        temp = "E";
      else if (value % 16 == 15)
        temp = "F";
      else 
        temp = "" + value % 16;
  
      return decimalToHex(value / 16) + temp;   
    }
  }
}
