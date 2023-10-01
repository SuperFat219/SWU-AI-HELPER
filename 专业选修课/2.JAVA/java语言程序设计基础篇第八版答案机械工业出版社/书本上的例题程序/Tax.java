public class Tax {
  public final static int SINGLE = 0;
  public final static int MARRIED_FILE_JOINT = 1;
  public final static int MARRIED_FILE_SEPARATE = 2;
  public final static int HEAD_OF_HOUSEHOLD = 3;
  private String taxPayer;
  private int status;
  private double income;

  /** Construct a Tax object */
  public Tax(String taxPayer, int status, double income) {
    this.taxPayer = taxPayer;
    this.status = status;
    this.income = income;
  }

  /** Return the tax payer of this object */
  public String getTaxPayer() {
    return taxPayer;
  }

  /** Return the tax status of this object */
  public int getStatus() {
    return status;
  }

  /** Return the taxable income of this object */
  public double getIncome() {
    return income;
  }

  /** Return the tax for of this object */
  public double getTax() {
    return getTax(status, income);
  }

  /** Return the tax for the specified status and income */
  public static double getTax(int status, double income) {
    switch (status) {
      case SINGLE:
        return getTax(income, 6000, 27950, 67700, 141250, 307050);
      case MARRIED_FILE_JOINT:
        return getTax(income, 12000, 46700, 112850, 171950, 307050);
      case MARRIED_FILE_SEPARATE:
        return getTax(income, 6000, 23350, 56425, 85975, 153525);
      case HEAD_OF_HOUSEHOLD:
        return getTax(income, 10000, 37450, 96700, 156600, 307050);
      default:
        return 0;
    }
  }

  private static double getTax(double income, 
      int r1, int r2, int r3, int r4, int r5) {
    double tax = 0;

    if (income <= r1) {
      tax = income * 0.10;
    }
    else if (income <= r2) {
      tax = r1 * 0.10 + (income - r1) * 0.15;
    }
    else if (income <= r3) {
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (income - r2) * 0.27;
    }
    else if (income <= r4) {
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.27 +
        (income - r3) * 0.30;
    }
    else if (income <= r5) {
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.27 + (r4 -
        r3) * 0.30 + (income - r4) * 0.35;
    }
    else {
      tax = r1 * 0.10 + (r2 - r1) * 0.15 + (r3 - r2) * 0.27 + (r4 -
        r3) * 0.30 + (r5 - r4) * 0.35 + (income - r5) * 0.386;
    }

    return Math.round(tax * 100) / 100.0;
  }
}
