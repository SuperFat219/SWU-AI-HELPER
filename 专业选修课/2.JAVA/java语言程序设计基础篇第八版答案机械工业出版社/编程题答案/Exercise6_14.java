public class Exercise6_14 {
  public static void main(String args[]) {
    System.out.print("Enter five integers: ");
    java.util.Scanner input = new java.util.Scanner(System.in);
    
    
    double number = input.nextDouble();
    
    System.out.println(
      "The square root for 9 is " + SquareRoot.sqrt(number));

    System.out.println(gcd(33, 33, 33, 11, 22));
  }

  public static int gcd(int... numbers) {
    int gcd = numbers[0];

    for (int i = 1; i < numbers.length; i++)
      gcd = gcd(gcd, numbers[i]);

    return gcd;
  }
  
  /** Return the gcd of two integers */
  public static int gcd(int n1, int n2) {
    int gcd = 1; // Initial gcd is 1
    int k = 1;   // Possible gcd

    while (k <= n1 && k <= n2) {
      if (n1 % k == 0 && n2 % k == 0)
        gcd = k; // Update gcd
      k++;
    }
  
    return gcd; // Return gcd
  }
}