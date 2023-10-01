public class UseTaxClass {
  public static void main(String[] args) {
    Tax tax1 = new Tax("John Doe", Tax.MARRIED_FILE_JOINT, 78525.16);
    System.out.println("The tax for " + tax1.getTaxPayer() + " is "
      + tax1.getTax() + "\n with filing status " + tax1.getStatus() +
      " and income " + tax1.getIncome());
    
    Tax tax2 = new Tax("Peter King", Tax.MARRIED_FILE_SEPARATE, 
      78525.16);
    System.out.println("The tax for " + tax2.getTaxPayer() + " is "
      + tax2.getTax() + "\n with filing status " + tax2.getStatus() +
      " and income " + tax2.getIncome()); 

    System.out.println("The tax is " + Tax.getTax(Tax.SINGLE, 40000) 
      + " for a single filer with $40000 income"); 
  }
}
