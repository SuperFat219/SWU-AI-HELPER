public class Exercise8_2 {
  public static void main(String[] args) {
    Stock stock = new Stock("JAVA", "Sun MicroSystems Inc.");
    stock.previousClosingPrice = 4.5;

    // Set current price
    stock.currentPrice = 4.35;

    // Display stock info
    System.out.println("Previous Closing Price: " +
      stock.previousClosingPrice);
    System.out.println("Current Price: " +
      stock.currentPrice);
    System.out.println("Price Change: " +
      stock.changePercent() * 100 + "%");
  }
}

class Stock {
  String symbol;
  String name;
  double previousClosingPrice;
  double currentPrice;

  public Stock() {
  }

  public Stock(String newSymbol, String newName) {
    symbol = newSymbol;
    name = newName;
  }

  public double changePercent() {
    return (currentPrice - previousClosingPrice) /
      previousClosingPrice;
  }
}
