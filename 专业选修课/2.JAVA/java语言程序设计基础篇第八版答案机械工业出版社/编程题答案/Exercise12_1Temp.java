
public class Exercise12_1Temp {
  public static void main(String[] args) {
    // Create a name
    Name name = new Name("John", 'D', "Smith");

    // Create an address
    Address address = new Address("100 Main Street", "Savannah",
      "GA", "31419");

    // Create a borrower
    Borrower borrower = new Borrower(name, address);

    borrower.addLoan(new Loan(5.5, 15, 250000));
    borrower.addLoan(new Loan(6.5, 15, 250000));
    borrower.addLoan(new Loan(7.5, 15, 250000));

    // Display loan information
    System.out.println(borrower);    
    
    java.util.ArrayList loans = borrower.getLoans();
    
    for (int i = 0; i < loans.size(); i++) {
      System.out.println("\nLoan " + (i + 1));
      Loan loan = (Loan)(loans.get(i));
      System.out.println("Loan amount: " + loan.getLoanAmount());
      System.out.println("Interest rate: " + loan.getAnnualInterestRate());
      System.out.println("Loan period: " + loan.getNumberOfYears());
      System.out.println("Monthly payment: " + loan.getMonthlyPayment());
      System.out.println("Total payment: " + loan.getTotalPayment());
    }
  }
}

final class Name implements Comparable {
  private String firstName;
  private char mi;
  private String lastName;

  /** Construct a name with firstName, mi, and lastName */
  public Name(String firstName, char mi, String lastName) {
    this.firstName = firstName;
    this.mi = mi;
    this.lastName = lastName;
  }

  /** Return firstName */
  public String getFirstName() {
    return firstName;
  }

  /** Return middle name initial */
  public char getMi() {
    return mi;
  }

  /** Return lastName */
  public String getLastname() {
    return lastName;
  }

  /** Obtain full name */
  public String getFullName() {
    return firstName + ' ' + mi + ' ' + lastName;
  }

  /** Implement compareTo in the Comparable interface */
  public int compareTo(Object o) {
    if (!lastName.equals(((Name)o).lastName)) {
      return lastName.compareTo(((Name)o).lastName);
    }
    else if (!firstName.equals(((Name)o).firstName)) {
      return firstName.compareTo(((Name)o).firstName);
    }
    else {
      return mi - ((Name)o).mi;
    }
  }
}  

final class Address {
  private String street;
  private String city;
  private String state;
  private String zip;

  /** Create an address with street, city, state, and zip */
  public Address(String street, String city,
    String state, String zip) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  /** Return street */
  public String getStreet() {
    return street;
  }

  /** Return city */
  public String getCity() {
    return city;
  }

  /** Return state */
  public String getState() {
    return state;
  }

  /** Return zip */
  public String getZip() {
    return zip;
  }

  /** Get full address */
  public String getFullAddress() {
    return street + '\n' + city + ", " + state + ' ' + zip + '\n';
  }
}

class Person1 {
  private Name name;
  private Address address;

  /** Construct a person with default properties */
  public Person1() {
    this(new Name("Jill", 'S', "Barr"),
      new Address("100 Main", "Savannah", "GA", "31411"));
  }

  /** Construct a person with specified name and address */
  public Person1(Name name, Address address) {
    this.name = name;
    this.address = address;
  }

  /** Return name */
  public Name getName() {
    return name;
  }

  /** Set a new name */
  public void setName(Name name) {
    this.name = name;
  }

  /** Return address */
  public Address getAddress() {
    return address;
  }

  /** Set a new address */
  public void setAddress(Address address) {
    this.address = address;
  }

  /** Override the toString method */
  public String toString() {
    return '\n' + name.getFullName() + '\n' +
      address.getFullAddress() + '\n';
  }

  /** Implement compareTo in the Comparable interface */
  public int compareTo(Object o) {
    return name.compareTo(((Person1)o).name);
  }
}

class Borrower extends Person1 {
  private java.util.ArrayList loans = new java.util.ArrayList();

  /** Construct a borrower with default properties */
  public Borrower() {
    super();
  }

  /** Create a borrower with specified name and address */
  public Borrower(Name name, Address address) {
    super(name, address);
  }

  /** Return loan */
  public java.util.ArrayList getLoans() {
    return loans;
  }

  /** Add a new loan */
  public void addLoan(Loan loan) {
    loans.add(loan);
  }

  /** Remove a loan */
  public void removeLoan(Loan loan) {
    loans.remove(loan);
  }
  
  /** String representation for borrower */
  public String toString() {
    return super.toString() + " has " + loans.size() + " loans";
  }
}
