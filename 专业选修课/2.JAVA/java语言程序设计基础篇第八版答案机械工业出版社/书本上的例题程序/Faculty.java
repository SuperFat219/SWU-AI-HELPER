public class Faculty extends Employee {
  public static void main(String[] args) {
    new Employee("Eric");
  }
  
  public Faculty() {
    super();
    System.out.println("(4) Faculty's no-arg constructor is invoked");
  }
}

class Employee extends PPP {
  public Employee() {
    this("(2) Invoke Employee’s overloaded constructor");
    System.out.println("(3) Employee's no-arg constructor is invoked");
  }

  public Employee(String s) {
    super();
    System.out.println(s);
  }
}

class PPP {
  public PPP() {
    System.out.println("(1) Person's no-arg constructor is invoked");
  }
}