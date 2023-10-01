public class Exercise20_24 {
  public static void main(String[] args) {
    System.out.println(hexToDecimal("A"));
    System.out.println(hexToDecimal("B"));
    System.out.println(hexToDecimal("C"));
    System.out.println(hexToDecimal("ABC"));
  }  

  public static int hexToDecimal(String hexString) {
    return hexToDecimal(hexString, 0, hexString.length() - 1);
  }
  
  public static int hexToDecimal(String hexString, int low, int high) {
    if (high < low)
      return 0;
    else {
      int temp;
      if (hexString.charAt(high) == 'A')
        temp = 10;
      else if (hexString.charAt(high) == 'B')
        temp = 11;
      else if (hexString.charAt(high) == 'C')
        temp = 12;
      else if (hexString.charAt(high) == 'D')
        temp = 13;
      else if (hexString.charAt(high) == 'E')
        temp = 14;
      else if (hexString.charAt(high) == 'F')
        temp = 15;
      else 
        temp = hexString.charAt(high) - '0';

      return hexToDecimal(hexString, low, high - 1) * 16
        + temp;
    }
  }
}
