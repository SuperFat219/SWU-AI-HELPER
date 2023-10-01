// Exercise12_2.java
public class Exercise12_2Temp {
  public static void main(String[] args) {
    new Exercise12_2Temp();
  }

  public Exercise12_2Temp() {
    // Define the arrays to be tested.
    Student[] students = new Student[3];
    students[0] = new Student(new Name("Derek", 'S', "Dexony"));
    students[1] = new Student(new Name("Stacy", 'M', "Waters"));
    students[2] = new Student(new Name("Adamo", 'U', "Leetmz"));

    String[] strings = {"Orange", "Jackblade", "Apple"};
    Integer[] i = {new Integer(5), new Integer(0), new Integer(3)};

    // Display current array of students
    printList(students);
    System.out.println();

    // Display max of students
    System.out.println("Max is " + max(students));
    System.out.println();

    // Display sorted students
    sort(students);
    printList(students);
    System.out.println();

    // Display current array of strings
    printList(strings);
    System.out.println();

    // Display max of strings
    System.out.println("Max is " + max(strings));
    System.out.println();

    // Display sorted strings
    sort(strings);
    printList(strings);
    System.out.println();

    // Display current array of intergers
    printList(i);
    System.out.println();

    // Display max of ints
    System.out.println("Max is " + max(i));
    System.out.println();

    // Display sorted ints
    sort(i);
    printList(i);
  }

  /** Pirnt an array of objects */
  public static void printList(Object[] object) {
    for (int i = 0; i < object.length; i++) {
      System.out.println(object[i]);
    }
  } // End of method printList(Object[])

  public static Comparable max(Comparable[] object) {
    // Find max of array object
    Comparable maxOfA = object[0];
    for (int i = 1; i < object.length; i++) {
      if (maxOfA.compareTo(object[i]) == -1) {
        maxOfA = object[i];
      }
    }

    // Return results.
    return maxOfA;
  } // End of method max(Object[])

  public static void sort(Comparable[] object) {
    // Set the variables for the current min, minIndex, and compare value
    Comparable currentMin;
    int currentMinIndex;
    int compareInt;

    // Begin loop to work through the list.
    for (int i = 0; i < object.length; i++) {
      // Find the minimum in the list.
      currentMin = object[i];
      currentMinIndex = i;
      for (int k = i + 1; k < object.length; k++) {
        compareInt = currentMin.compareTo(object[k]);
        if (compareInt > 0) {
          currentMin = object[k];
          currentMinIndex = k;
        }
      }

      // Swap list[i] with list[currentMaxIndex] if needed.
      if (currentMinIndex != i) {
        object[currentMinIndex] = object[i];
        object[i] = currentMin;
      }
    }
  } // End of instance method sort(Object[])

  class Student extends Person implements Comparable, Cloneable {
    // Variables
    private String major;

    // Constructors
    public Student(Name name) {
      super(name);
      major = "Computer Science";
    }

    // Default Constructor
    public Student() {

    }

    // Instance Methods
    public String getMajor() {
      return major;
    }

    public void setMajor(String major) {
      this.major = major;
    }

    public String toString() {
      Name name = this.getName();
      return name.getFullName() + '\n' + "Major: " + major;
    }

    public boolean equals(Object object) {
      Name name = this.getName();
      Name otherName = ((Student)object).getName();

      if ((name.getFullName().equals(otherName.getLastName()))
          && (major.equals(((Student)object).getMajor()))) {
        return true;
      }
      return false;
    }

    /** Compare studnet's major, then last name */
    public int compareTo(Object object) {
      if (major.compareTo(((Student)object).major) == 0) {
        return super.compareTo(object);
      }
      else {
        return major.compareTo(((Student)object).major);
      }
    }
  }

  public class Person {
    // Variables
    private Name name;

    // Constructors
    public Person(Name name) {
      this.name = name;
    }

    // Default Constructor
    public Person() {
    }

    // Instance Methods
    public Name getName() {
      return name;
    }

    public void setName(Name name) {
      this.name = name;
    }

    public String toString() {
      return name.getFullName();
    }

    public boolean equals(Object object) {
      Name otherName = ((Person)object).getName();
      if (name.getFullName().equals(otherName.getFullName())) {
        return true;
      }
      return false;
    }

    /** Compare person's last name */
    public int compareTo(Object object) {
      Name name = this.getName();
      String lastName = name.getLastName();
      Name otherName = ((Student)object).getName();
      String otherLastName = otherName.getLastName();
      int i = 0;

      if ((lastName.compareTo(otherLastName)) > 0) {
        i = 1;
      }
      else if ((lastName.compareTo(otherLastName)) < 0) {
        i = -1;
      }
      return i;
    }
  }

  class Name {
    // Variables
    private String firstName;
    private char mi;
    private String lastName;

    // Constructors
    public Name(String firstName, char mi, String lastName) {
      // Set new variables to instance.
      this.firstName = firstName;
      this.mi = mi;
      this.lastName = lastName;
    }

    // Default Constructor
    public Name() {

    }

    // Instance Methods
    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public char getMi() {
      return mi;
    }

    public void setMi(char mi) {
      this.mi = mi;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getFullName() {
      return firstName + " " + mi + " " + lastName;
    }
  }
}
