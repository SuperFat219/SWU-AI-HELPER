public class Exercise5_8 {
  public static void main(String[] args) {
    System.out.println("Celsius\t\tFahrenheit\t|\tFahrenheit\tCelsius");
    System.out.println("---------------------------------------------");

    double celsius = 40; double farenheit = 120;
    for (int i = 1; i <= 10; celsius--, farenheit -= 10, i++) {
      System.out.println(celsius + "\t\t" + celsiusToFahrenheit(celsius) + "\t|\t" + farenheit + "\t\t" + fahrenheitToCelsius(farenheit));
    }
  }

  public static double celsiusToFahrenheit(double celsius) {
    return (9.0 / 5.0) * celsius + 32;
  }

  public static double fahrenheitToCelsius(double fahrenheit) {
    return (5.0 / 9) * (fahrenheit - 32);
  }
}
